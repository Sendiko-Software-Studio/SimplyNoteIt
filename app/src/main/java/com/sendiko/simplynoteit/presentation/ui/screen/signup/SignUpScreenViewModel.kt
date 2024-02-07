package com.sendiko.simplynoteit.presentation.ui.screen.signup

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.sendiko.simplynoteit.data.requests.SignUpRequest
import com.sendiko.simplynoteit.data.responses.SignUpResponse
import com.sendiko.simplynoteit.domain.repositories.UserRepository
import com.sendiko.simplynoteit.presentation.ui.helper.ErrorTextField
import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class SignUpScreenViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(SignUpScreenState())
    val state = _state.asStateFlow()

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun isDataValid(): Boolean {
        if (state.value.usernameText.isBlank()) {
            _state.update {
                it.copy(
                    isUsernameFieldError = ErrorTextField(
                        isError = true,
                        errorMessage = "Username can't be empty."
                    )
                )
            }
            return false
        }
        if (!isValidEmail(state.value.emailText)) {
            _state.update {
                it.copy(
                    isEmailTextFieldError = ErrorTextField(
                        isError = true,
                        errorMessage = "Email have to be valid address."
                    )
                )
            }
            return false
        }
        if (state.value.passwordText.isBlank()) {
            _state.update {
                it.copy(
                    isPasswordFieldError = ErrorTextField(
                        isError = true,
                        errorMessage = "Password can't be empty."
                    )
                )
            }
            return false
        }
        return true
    }

    private fun signUp() {
        _state.update { it.copy(isLoading = true) }
        val request = repo.signUp(
            SignUpRequest(
                name = state.value.usernameText,
                email = state.value.emailText,
                password = state.value.passwordText,
                passwordConfirmation = state.value.passwordText
            )
        )

        request.enqueue(
            object : Callback<SignUpResponse> {
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        201 -> _state.update {
                            it.copy(isSignedUpSuccess = true)
                        }

                        422 -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Email already taken."
                                ),
                                notificationMessage = "Email already taken."
                            )
                        }

                        500 -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                ),
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            isRequestFailed = FailedRequest(
                                isFailed = true,
                                failedMessage = t.message.toString()
                            ),
                            notificationMessage = "Server error."
                        )
                    }
                }

            }
        )
    }

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.OnUsernameChanged -> _state.update {
                it.copy(
                    usernameText = event.username,
                    isUsernameFieldError = ErrorTextField(isError = false, errorMessage = "")
                )
            }

            is SignUpScreenEvent.OnPasswordChanged -> _state.update {
                it.copy(
                    passwordText = event.password,
                    isPasswordFieldError = ErrorTextField(isError = false, errorMessage = "")
                )
            }

            SignUpScreenEvent.OnPasswordCleared -> _state.update {
                it.copy(
                    passwordText = "",
                    isPasswordFieldError = ErrorTextField(isError = false, errorMessage = "")
                )
            }

            is SignUpScreenEvent.OnPasswordVisibilityChanged -> _state.update {
                it.copy(isPasswordVisible = event.isVisible)
            }

            SignUpScreenEvent.OnUsernameCleared -> _state.update {
                it.copy(
                    usernameText = "",
                    isUsernameFieldError = ErrorTextField(isError = false, errorMessage = "")
                )
            }

            is SignUpScreenEvent.OnEmailChanged -> _state.update {
                it.copy(
                    emailText = event.email,
                    isEmailTextFieldError = ErrorTextField(isError = false, errorMessage = "")
                )
            }

            SignUpScreenEvent.OnEmailCleared -> _state.update {
                it.copy(
                    emailText = "",
                    isEmailTextFieldError = ErrorTextField(isError = false, errorMessage = "")
                )
            }

            is SignUpScreenEvent.SetFailedMessage -> _state.update {
                it.copy(
                    isRequestFailed = FailedRequest(
                        isFailed = event.isFailed,
                        failedMessage = ""
                    )
                )
            }

            is SignUpScreenEvent.OnSignUp -> if (isDataValid()) signUp()
        }
    }
}
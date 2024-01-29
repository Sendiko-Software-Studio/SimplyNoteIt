package com.sendiko.simplynoteit.presentation.ui.screen.signin

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.simplynoteit.data.requests.SignInRequest
import com.sendiko.simplynoteit.data.responses.SignInResponse
import com.sendiko.simplynoteit.domain.repositories.UserRepository
import com.sendiko.simplynoteit.presentation.ui.helper.ErrorTextField
import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(SignInScreenState())
    val state = _state.asStateFlow()

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun isDataValid(): Boolean {
        if (!isValidEmail(state.value.emailText)) {
            _state.update {
                it.copy(
                    isEmailFieldError = ErrorTextField(
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

    private fun signIn() {
        _state.update { it.copy(isLoading = true, isRequestFailed = (FailedRequest(false, ""))) }
        val request = repo.signIn(
            SignInRequest(
                email = state.value.emailText,
                password = state.value.passwordText
            )
        )
        request.enqueue(
            object : Callback<SignInResponse> {
                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }

                    when (response.code()) {
                        200 -> viewModelScope.launch {
                            response.body()?.user?.let { repo.saveUsername(it.name) }
                            response.body()?.let { repo.setToken(it.token) }
                            response.body()?.user?.let { repo.setId(it.id.toString()) }
                            _state.update {
                                it.copy(isSuccessfullySignedIn = true)
                            }
                        }
                        401 -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Password salah."
                                )
                            )
                        }
                        404 -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Akun tidak ditemukan."
                                )
                            )
                        }
                        422 -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Data harus lengkap."
                                )
                            )
                        }
                        500 -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestFailed = FailedRequest(
                                isFailed = true,
                                failedMessage = t.message.toString()
                            )
                        )
                    }
                }
            }
        )
    }

    fun onEvent(event: SignInScreenEvents) {
        when (event) {
            is SignInScreenEvents.OnEmailChanged -> _state.update {
                it.copy(
                    emailText = event.username,
                    isEmailFieldError = ErrorTextField(
                        isError = false,
                        errorMessage = ""
                    )
                )
            }

            is SignInScreenEvents.OnPasswordChanged -> _state.update {
                it.copy(
                    passwordText = event.password,
                    isPasswordFieldError = ErrorTextField(
                        isError = false,
                        errorMessage = ""
                    )
                )
            }

            SignInScreenEvents.OnPasswordCleared -> _state.update {
                it.copy(passwordText = "")
            }

            is SignInScreenEvents.OnPasswordVisibilityChanged -> _state.update {
                it.copy(isPasswordVisible = event.isVisible)
            }

            SignInScreenEvents.OnEmailCleared -> _state.update {
                it.copy(emailText = "")
            }

            is SignInScreenEvents.SetFailedMessage -> _state.update {
                it.copy(isRequestFailed = FailedRequest(event.isFailed, ""))
            }
            is SignInScreenEvents.OnSignIn -> if (isDataValid()) signIn()
        }
    }
}
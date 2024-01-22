package com.sendiko.simplynoteit.presentation.ui.screen.signin

import androidx.lifecycle.ViewModel
import com.sendiko.simplynoteit.data.requests.SignInRequest
import com.sendiko.simplynoteit.data.responses.SignInResponse
import com.sendiko.simplynoteit.domain.repositories.UserRepository
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
class SignInScreenViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(SignInScreenState())
    val state = _state.asStateFlow()

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
                        200 ->
                            _state.update {
                                it.copy(isSuccessfullySignedIn = true)
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
                it.copy(emailText = event.username)
            }

            is SignInScreenEvents.OnPasswordChanged -> _state.update {
                it.copy(passwordText = event.password)
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

            is SignInScreenEvents.OnSignIn -> signIn()
            is SignInScreenEvents.SetFailedMessage -> _state.update {
                it.copy(isRequestFailed = FailedRequest(event.isFailed, ""))
            }
        }
    }
}
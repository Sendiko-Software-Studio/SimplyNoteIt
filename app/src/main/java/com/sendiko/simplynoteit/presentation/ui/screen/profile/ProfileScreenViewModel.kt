package com.sendiko.simplynoteit.presentation.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.simplynoteit.data.responses.GetUserResponse
import com.sendiko.simplynoteit.data.responses.SignOutResponse
import com.sendiko.simplynoteit.domain.repositories.UserRepository
import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenState())
    private val _token = userRepository.getToken()
    private val _userId = userRepository.getId()
    val state = combine(flow = _token, flow2 = _userId, flow3 = _state) { token, userId, state ->
        state.copy(
            token = "Bearer $token",
            userId = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileScreenState())

    private fun getUser() {
        _state.update { it.copy(isLoading = true) }
        val request = userRepository.getUser(
            userId = state.value.userId,
            token = state.value.token
        )
        request.enqueue(
            object : Callback<GetUserResponse> {
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    response.body()?.user.let { user ->
                        user?.let {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    name = user.name,
                                    email = user.email,
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
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

    private fun logout() {
        _state.update { it.copy(isLoading = true) }
        val request = userRepository.signOut(state.value.token)
        request.enqueue(
            object : Callback<SignOutResponse> {
                override fun onResponse(
                    call: Call<SignOutResponse>,
                    response: Response<SignOutResponse>
                ) {
                    viewModelScope.launch {
                        userRepository.setToken("")
                        userRepository.setId("")
                        userRepository.saveUsername("")
                    }
                    _state.update { it.copy(isLoading = false, isSignedOutSuccessfully = true) }
                }

                override fun onFailure(call: Call<SignOutResponse>, t: Throwable) {
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

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.OnChangePasswordClick -> _state.update {
                it.copy(isSheetVisible = true)
            }

            is ProfileScreenEvent.OnPasswordEdit -> _state.update {
                it.copy(password = event.password)
            }

            ProfileScreenEvent.OnLogoutClick -> logout()
            ProfileScreenEvent.OnGetUserInfo -> getUser()
        }
    }
}
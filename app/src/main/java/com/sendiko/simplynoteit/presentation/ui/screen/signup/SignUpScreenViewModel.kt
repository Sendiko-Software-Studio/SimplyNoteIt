package com.sendiko.simplynoteit.presentation.ui.screen.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignUpScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignUpScreenEvent){
        when(event){
            is SignUpScreenEvent.OnUsernameChanged -> _state.update {
                it.copy(usernameText = event.username)
            }
            is SignUpScreenEvent.OnPasswordChanged -> _state.update {
                it.copy(passwordText = event.password)
            }
            SignUpScreenEvent.OnPasswordCleared -> _state.update {
                it.copy(passwordText = "")
            }
            is SignUpScreenEvent.OnPasswordVisibilityChanged -> _state.update {
                it.copy(isPasswordVisible = event.isVisible)
            }
            SignUpScreenEvent.OnUsernameCleared -> _state.update {
                it.copy(usernameText = "")
            }
            is SignUpScreenEvent.OnEmailChanged -> _state.update {
                it.copy(emailText = event.email)
            }
            SignUpScreenEvent.OnEmailCleared -> _state.update {
                it.copy(emailText = "")
            }
            is SignUpScreenEvent.OnSignUp -> TODO()
        }
    }
}
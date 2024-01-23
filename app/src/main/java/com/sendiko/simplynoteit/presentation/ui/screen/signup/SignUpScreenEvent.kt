package com.sendiko.simplynoteit.presentation.ui.screen.signup

sealed class SignUpScreenEvent {
    data class OnUsernameChanged(val username: String): SignUpScreenEvent()
    data class OnEmailChanged(val email: String): SignUpScreenEvent()
    data class OnPasswordChanged(val password: String): SignUpScreenEvent()
    data object OnUsernameCleared: SignUpScreenEvent()
    data object OnPasswordCleared: SignUpScreenEvent()
    data object OnEmailCleared: SignUpScreenEvent()
    data class OnPasswordVisibilityChanged(val isVisible: Boolean): SignUpScreenEvent()
    data class OnSignUp(val username: String, val password: String): SignUpScreenEvent()
    data class SetFailedMessage(val isFailed: Boolean): SignUpScreenEvent()
}
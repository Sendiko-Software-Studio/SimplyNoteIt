package com.sendiko.simplynoteit.presentation.ui.screen.signin

sealed class SignInScreenEvents {
    data class OnEmailChanged(val username: String): SignInScreenEvents()
    data class OnPasswordChanged(val password: String): SignInScreenEvents()
    data object OnEmailCleared: SignInScreenEvents()
    data object OnPasswordCleared: SignInScreenEvents()
    data class OnPasswordVisibilityChanged(val isVisible: Boolean): SignInScreenEvents()
    data class OnSignIn(val username: String, val password: String): SignInScreenEvents()
    data class SetFailedMessage(val isFailed: Boolean): SignInScreenEvents()
}
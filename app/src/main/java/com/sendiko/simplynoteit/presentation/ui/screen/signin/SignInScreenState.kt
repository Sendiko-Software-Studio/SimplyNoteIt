package com.sendiko.simplynoteit.presentation.ui.screen.signin

import com.sendiko.simplynoteit.presentation.ui.helper.ErrorTextField
import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest

data class SignInScreenState(
    val emailText: String = "",
    val isEmailFieldError: ErrorTextField = ErrorTextField(),
    val passwordText: String = "",
    val isPasswordFieldError: ErrorTextField = ErrorTextField(),
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isRequestFailed: FailedRequest = FailedRequest(),
    val isSuccessfullySignedIn: Boolean = false,
)

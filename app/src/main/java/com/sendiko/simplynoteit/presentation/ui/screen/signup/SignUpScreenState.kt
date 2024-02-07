package com.sendiko.simplynoteit.presentation.ui.screen.signup

import com.sendiko.simplynoteit.presentation.ui.helper.ErrorTextField
import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest

data class SignUpScreenState(
    val usernameText: String = "",
    val isUsernameFieldError: ErrorTextField = ErrorTextField(),
    val passwordText: String = "",
    val isPasswordFieldError: ErrorTextField = ErrorTextField(),
    val isPasswordVisible: Boolean = false,
    val emailText: String = "",
    val isEmailTextFieldError: ErrorTextField = ErrorTextField(),
    val isLoading: Boolean = false,
    val isSignedUpSuccess: Boolean = false,
    val isRequestFailed: FailedRequest = FailedRequest(),
    val notificationMessage: String = "",
)

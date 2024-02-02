package com.sendiko.simplynoteit.presentation.ui.screen.profile

import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest

data class ProfileScreenState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isSheetVisible: Boolean = false,
    val token: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: FailedRequest = FailedRequest(),
    val notificationMessage: String = "",
    val isSignedOutSuccessfully : Boolean = false,
    val userId: String = "",
)

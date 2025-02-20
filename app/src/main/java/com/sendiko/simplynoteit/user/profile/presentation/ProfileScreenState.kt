package com.sendiko.simplynoteit.user.profile.presentation

import com.sendiko.simplynoteit.core.ui.helper.FailedRequest

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

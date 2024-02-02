package com.sendiko.simplynoteit.presentation.ui.screen.profile

sealed class ProfileScreenEvent {

    data object OnLogoutClick: ProfileScreenEvent()
    data class OnChangePasswordClick(val isVisible: Boolean): ProfileScreenEvent()
    data class OnPasswordEdit(val password: String): ProfileScreenEvent()
    data object OnGetUserInfo: ProfileScreenEvent()
}
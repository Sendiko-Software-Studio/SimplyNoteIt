package com.sendiko.simplynoteit.profile.presentation

sealed class ProfileScreenEvent {

    data object OnLogoutClick: ProfileScreenEvent()
    data class OnChangePasswordClick(val isVisible: Boolean): ProfileScreenEvent()
    data class OnPasswordEdit(val password: String): ProfileScreenEvent()
    data object OnGetUserInfo: ProfileScreenEvent()
}
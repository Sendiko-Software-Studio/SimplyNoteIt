package com.sendiko.simplynoteit.presentation.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.simplynoteit.domain.preference.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(preferences: AppPreferences): ViewModel() {

    private val _state = MutableStateFlow(SplashScreenState())
    private val _username = preferences.getName()
    val state = combine(flow = _username, flow2 = _state) { username, state ->
        state.copy(
            isLoggedIn = username.isNotBlank()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashScreenState())
}
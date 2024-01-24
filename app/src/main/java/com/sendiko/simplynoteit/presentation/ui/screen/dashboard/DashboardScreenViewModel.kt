package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.simplynoteit.domain.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    repository: UserRepository
): ViewModel() {

    private val _state = MutableStateFlow(DashboardScreenState())
    private val _username = repository.getUsername()
    private val _token = repository.getToken()
    val state = combine(flow = _username, flow2 = _token, flow3 = _state) { username, token, state ->
        state.copy(
            name = username,
            token = token
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardScreenState())

    fun onEvent(events: DashboardScreenEvents){
        when(events){
            is DashboardScreenEvents.OnSetCheckedTaskVisible -> _state.update {
                it.copy(isCheckedTaskVisible = events.isVisible)
            }
            is DashboardScreenEvents.OnTaskClick -> _state.update {
                it.copy(
                    isTaskSheetExpanded = !it.isTaskSheetExpanded,
                    task = events.task
                )
            }
            is DashboardScreenEvents.OnTaskDescriptionChange -> _state.update {
                it.copy(descriptionText = events.description)
            }
            is DashboardScreenEvents.OnTaskTitleChange -> _state.update {
                it.copy(taskText = events.title)
            }
            is DashboardScreenEvents.OnTaskSheetVisibilityChanged -> _state.update {
                it.copy(isTaskSheetExpanded = events.isVisible)
            }
            is DashboardScreenEvents.OnCreateTask -> TODO()
            is DashboardScreenEvents.OnCheckChange -> TODO()
            is DashboardScreenEvents.OnUpdateTask -> TODO()
        }
    }
}
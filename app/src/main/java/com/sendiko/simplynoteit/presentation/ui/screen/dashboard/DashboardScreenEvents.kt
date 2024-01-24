package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import com.sendiko.simplynoteit.data.responses.TaskItem

sealed class DashboardScreenEvents {
    data class OnCheckChange(val isChecked: Boolean): DashboardScreenEvents()
    data class OnTaskClick(val task: TaskItem): DashboardScreenEvents()
    data class OnTaskTitleChange(val title: String): DashboardScreenEvents()
    data class OnTaskDescriptionChange(val description: String): DashboardScreenEvents()
    data class OnCreateTask(val task: TaskItem): DashboardScreenEvents()
    data class OnUpdateTask(val task: TaskItem): DashboardScreenEvents()
    data class OnSetCheckedTaskVisible(val isVisible: Boolean): DashboardScreenEvents()
    data class OnTaskSheetVisibilityChanged(val isVisible: Boolean): DashboardScreenEvents()
}
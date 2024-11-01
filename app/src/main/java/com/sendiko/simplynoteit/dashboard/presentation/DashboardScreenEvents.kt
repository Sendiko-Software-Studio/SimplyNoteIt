package com.sendiko.simplynoteit.dashboard.presentation

import com.sendiko.simplynoteit.dashboard.data.TaskItem
import com.sendiko.simplynoteit.core.ui.helper.SortBy
import com.sendiko.simplynoteit.core.ui.helper.TaskAction

sealed class DashboardScreenEvents {
    data class OnCheckChange(val task: TaskItem): DashboardScreenEvents()
    data class OnTaskClick(val task: TaskItem): DashboardScreenEvents()
    data class OnTaskTitleChange(val title: String): DashboardScreenEvents()
    data object OnTaskTitleClear: DashboardScreenEvents()
    data class OnTaskDescriptionChange(val description: String): DashboardScreenEvents()
    data object OnNotificationMessageClear: DashboardScreenEvents()
    data object OnFailedRequestStateClear: DashboardScreenEvents()
    data class OnSetSortBy(val sortBy: SortBy): DashboardScreenEvents()
    data object OnTaskDescClear: DashboardScreenEvents()
    data object OnCreateTask: DashboardScreenEvents()
    data object OnUpdateTask: DashboardScreenEvents()
    data object OnDeleteTask: DashboardScreenEvents()
    data class OnSetCheckedTaskVisible(val isVisible: Boolean): DashboardScreenEvents()
    data class OnTaskSheetVisibilityChanged(val isVisible: Boolean, val taskAction: TaskAction): DashboardScreenEvents()
    data object OnTaskLoad: DashboardScreenEvents()
}
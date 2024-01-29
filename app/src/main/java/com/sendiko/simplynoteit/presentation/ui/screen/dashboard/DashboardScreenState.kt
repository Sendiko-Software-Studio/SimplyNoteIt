package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import com.sendiko.simplynoteit.data.responses.TaskItem
import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest

data class DashboardScreenState(
    val tasks: List<TaskItem> = emptyList(),
    val name: String = "",
    val taskSheetState: TaskSheetState = TaskSheetState(),
    val sortBy: SortBy = SortBy.ID,
    val isCheckedTaskVisible: Boolean = false,
    val task: TaskItem? = null,
    val token: String = "",
    val notificationMessage: String = "",
    val isRequestFailed: FailedRequest = FailedRequest(),
    val isLoading: Boolean = false,
    val userId: String = "",
)

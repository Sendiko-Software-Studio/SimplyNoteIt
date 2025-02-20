package com.sendiko.simplynoteit.task.dashboard.presentation

import com.sendiko.simplynoteit.task.dashboard.data.TaskItem
import com.sendiko.simplynoteit.core.ui.helper.FailedRequest
import com.sendiko.simplynoteit.task.core.presentation.SortBy
import com.sendiko.simplynoteit.task.core.presentation.TaskSheetState

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

package com.sendiko.simplynoteit.dashboard.presentation

import com.sendiko.simplynoteit.dashboard.data.TaskItem
import com.sendiko.simplynoteit.core.ui.helper.FailedRequest
import com.sendiko.simplynoteit.core.ui.helper.SortBy
import com.sendiko.simplynoteit.core.ui.helper.TaskSheetState

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

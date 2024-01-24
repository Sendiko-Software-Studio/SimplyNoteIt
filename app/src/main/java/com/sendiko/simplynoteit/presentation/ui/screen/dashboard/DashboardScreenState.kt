package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import com.sendiko.simplynoteit.data.responses.TaskItem
import com.sendiko.simplynoteit.presentation.ui.helper.ErrorTextField

data class DashboardScreenState(
    val tasks: List<TaskItem> = emptyList(),
    val name: String = "",
    val isTaskSheetExpanded: Boolean = false,
    val taskText: String = "",
    val isTaskFieldError: ErrorTextField = ErrorTextField(),
    val descriptionText: String = "",
    val isDescriptionFieldError: ErrorTextField = ErrorTextField(),
    val sortBy: SortBy = SortBy.ID,
    val isCheckedTaskVisible: Boolean = false,
    val task: TaskItem? = null,
    val token: String = "",
    val notificationMessage: String = "",
)

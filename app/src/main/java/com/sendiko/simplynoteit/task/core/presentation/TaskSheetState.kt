package com.sendiko.simplynoteit.task.core.presentation

import com.sendiko.simplynoteit.core.ui.helper.ErrorTextField

data class TaskSheetState(
    val isVisible: Boolean = false,
    val action: TaskAction = TaskAction.None,
    val taskText: String = "",
    val isTaskFieldError: ErrorTextField = ErrorTextField(),
    val descriptionText: String = "",
    val isDescriptionFieldError: ErrorTextField = ErrorTextField(),
)

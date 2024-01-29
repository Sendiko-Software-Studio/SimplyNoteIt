package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import com.sendiko.simplynoteit.presentation.ui.helper.ErrorTextField

data class TaskSheetState(
    val isVisible: Boolean = false,
    val action: TaskAction = TaskAction.None,
    val taskText: String = "",
    val isTaskFieldError: ErrorTextField = ErrorTextField(),
    val descriptionText: String = "",
    val isDescriptionFieldError: ErrorTextField = ErrorTextField(),
)

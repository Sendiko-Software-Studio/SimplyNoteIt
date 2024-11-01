package com.sendiko.simplynoteit.core.ui.helper

data class TaskSheetState(
    val isVisible: Boolean = false,
    val action: TaskAction = TaskAction.None,
    val taskText: String = "",
    val isTaskFieldError: ErrorTextField = ErrorTextField(),
    val descriptionText: String = "",
    val isDescriptionFieldError: ErrorTextField = ErrorTextField(),
)

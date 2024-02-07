package com.sendiko.simplynoteit.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Create
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Delete
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.None
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Read
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Update
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskSheetState
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSheet(
    state: TaskSheetState,
    onDismissRequest: () -> Unit,
    onTaskTitleChange: (String) -> Unit,
    onTaskTitleClear: () -> Unit,
    onTaskDescChange: (String) -> Unit,
    onTaskDescClear: () -> Unit,
    onTaskAction: (action: TaskAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val title = when (state.action) {
        Create -> "Create new"
        Read -> "Your"
        Update -> "Edit your"
        None -> ""
        Delete -> "Delete"
    }
    val buttonText = when (state.action) {
        Create -> "Create"
        Read -> "Your task"
        Update -> "Update"
        None -> ""
        Delete -> "Delete"
    }
    LaunchedEffect(
        key1 = state.isVisible,
        block = {
            if (state.isVisible) {
                sheetState.show()
            } else sheetState.hide()
        }
    )
    if (state.isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            content = {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Top),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$title task",
                            fontFamily = nunitoFont,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (state.action != Create){
                            IconButton(
                                onClick = {
                                    onTaskAction(Delete)
                                },
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete"
                                    )
                                },
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = MaterialTheme.colorScheme.onError
                                )
                            )
                        }
                    }
                    OutlinedTextField(
                        value = state.taskText,
                        onNewValue = {
                            onTaskTitleChange(it)
                        },
                        label = "Title",
                        hint = "ex: Do laundry...",
                        leadingIcon = Icons.Default.TextFields,
                        onClearValue = onTaskTitleClear,
                        isPasswordVisible = true
                    )
                    OutlinedTextField(
                        value = state.descriptionText,
                        onNewValue = {
                            onTaskDescChange(it)
                        },
                        label = "Description",
                        hint = "ex: Paid for Rp. 24.000",
                        leadingIcon = Icons.Default.TextSnippet,
                        onClearValue = onTaskDescClear,
                        isPasswordVisible = true
                    )
                    FilledButton(
                        text = buttonText,
                        onClick = {
                            onTaskAction(state.action)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = state.taskText.isNotBlank()
                    )
                }
            }
        )
    }
}
package com.sendiko.simplynoteit.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.data.responses.TaskItem
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

@Composable
fun CompletedTaskItem(
    task: TaskItem,
    onCheckChange: (TaskItem) -> Unit,
    onTaskClick: (TaskItem) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = task.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = nunitoFont,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Text(
                text = if (isExpanded) task.description else task.description.take(24) + if (task.description.length > 24) "..." else "",
                fontFamily = nunitoFont,
                modifier = Modifier
                    .clickable {
                        isExpanded = !isExpanded
                    }
                    .padding(end = 24.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        Checkbox(
            checked = task.isDone == "1",
            onCheckedChange = {
                onCheckChange(
                    TaskItem(
                        title = task.title,
                        description = task.description,
                        isDone = "0",
                        userId = task.userId,
                        id = task.id,
                        createdAt = task.createdAt,
                        updatedAt = task.updatedAt
                    )
                )
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}
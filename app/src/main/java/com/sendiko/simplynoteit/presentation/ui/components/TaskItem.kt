package com.sendiko.simplynoteit.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.data.responses.TaskItem
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

@Composable
fun TaskItem(
    task: TaskItem,
    onCheckChange: (Boolean) -> Unit,
    onTaskClick: (TaskItem) -> Unit
) {
    val isDone = task.isDone == 1
    Row(
        modifier = Modifier.fillMaxWidth()
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
                fontWeight = Bold,
                fontSize = 18.sp,
                fontFamily = nunitoFont
            )
            Text(
                text = task.description.take(24) + if (task.description.length > 24) "..." else "",
                fontFamily = nunitoFont
            )
        }
        Checkbox(
            checked = isDone,
            onCheckedChange = {
                onCheckChange(!isDone)
            },
        )
    }
}

@Preview
@Composable
fun TaskItemPrev() {
    Surface {
        TaskItem(
            task = TaskItem(
                id = 1,
                userId = 1,
                title = "This",
                description = "aslkdfj",
                createdAt = "0",
                isDone = 0,
                updatedAt = "0"
            ),
            onCheckChange = {

            },
            onTaskClick = {

            }
        )
    }
}


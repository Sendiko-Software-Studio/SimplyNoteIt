package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sendiko.simplynoteit.R
import com.sendiko.simplynoteit.data.responses.TaskItem
import com.sendiko.simplynoteit.presentation.ui.components.CompletedTaskItem
import com.sendiko.simplynoteit.presentation.ui.components.ContentBoxWithNotification
import com.sendiko.simplynoteit.presentation.ui.components.LoadingTaskItem
import com.sendiko.simplynoteit.presentation.ui.components.TaskItem
import com.sendiko.simplynoteit.presentation.ui.components.TaskSheet
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Create
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Delete
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.None
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Read
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Update
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardScreenState,
    onEvent: (DashboardScreenEvents) -> Unit,
    onNavigate: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val tasks = state.tasks.filter {
        it.isDone == "0"
    }
    val completedTasks = state.tasks.filter {
        it.isDone == "1"
    }
    LaunchedEffect(
        key1 = state.tasks,
        key2 = state.notificationMessage,
        block = {
            if (state.tasks == emptyList<TaskItem>())
                onEvent(DashboardScreenEvents.OnTaskLoad)

            if (state.notificationMessage.isNotBlank())
                delay(2000)
            onEvent(DashboardScreenEvents.OnNotificationMessageClear)
            onEvent(DashboardScreenEvents.OnFailedRequestStateClear)
        }
    )
    ContentBoxWithNotification(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestFailed.isFailed
    ) {
        Scaffold(
            topBar = {
                MediumTopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = {
                        Text(
                            text = "Hellow, ${state.name}",
                            fontFamily = nunitoFont,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = { onNavigate(Destinations.ProfileScreenDestination.destination) },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Account",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        TextButton(
                            onClick = {
                                onEvent(
                                    DashboardScreenEvents.OnSetSortBy(
                                        sortBy = if (state.sortBy == SortBy.ID) SortBy.DateCreated else SortBy.ID
                                    )
                                )
                            },
                            content = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    content = {
                                        Icon(
                                            imageVector = Icons.Default.SwapVert,
                                            contentDescription = "reverse list"
                                        )
                                        Text(text = if (state.sortBy == SortBy.ID) "First created" else "Newly created")
                                    }
                                )
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                onEvent(
                                    DashboardScreenEvents.OnTaskSheetVisibilityChanged(
                                        isVisible = true,
                                        Create
                                    )
                                )
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add tasks"
                                )
                            },
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        )
                    }
                )
            }
        ) { paddingValues ->
            TaskSheet(
                state = state.taskSheetState,
                onDismissRequest = {
                    onEvent(
                        DashboardScreenEvents.OnTaskSheetVisibilityChanged(
                            isVisible = false,
                            taskAction = None
                        )
                    )
                },
                onTaskTitleChange = {
                    onEvent(DashboardScreenEvents.OnTaskTitleChange(it))
                },
                onTaskTitleClear = {
                    onEvent(DashboardScreenEvents.OnTaskTitleClear)
                },
                onTaskDescChange = {
                    onEvent(DashboardScreenEvents.OnTaskDescriptionChange(it))
                },
                onTaskDescClear = {
                    onEvent(DashboardScreenEvents.OnTaskDescClear)
                },
                onTaskAction = {
                    when (it) {
                        Create -> onEvent(DashboardScreenEvents.OnCreateTask)
                        Update -> onEvent(DashboardScreenEvents.OnUpdateTask)
                        Delete -> onEvent(DashboardScreenEvents.OnDeleteTask)
                        Read -> null
                        None -> null
                    }
                }
            )
            LazyColumn(
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
                content = {
                    item {
                        AnimatedVisibility(
                            visible = state.tasks == emptyList<TaskItem>() && !state.isLoading,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.add_tasks),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    items(5) {
                        AnimatedVisibility(
                            visible = state.isLoading,
                            enter = expandVertically(),
                            exit = shrinkVertically(),
                        ) {
                            LoadingTaskItem()
                        }
                    }
                    items(tasks) { task ->
                        AnimatedVisibility(
                            visible = !state.isLoading,
                            enter = expandVertically(),
                            exit = shrinkVertically(),
                        ) {
                            TaskItem(
                                task = task,
                                onCheckChange = {
                                    onEvent(DashboardScreenEvents.OnCheckChange(it))
                                },
                                onTaskClick = {
                                    onEvent(DashboardScreenEvents.OnTaskClick(task))
                                }
                            )
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = !state.isLoading && completedTasks != emptyList<TaskItem>(),
                            enter = expandVertically(),
                            exit = shrinkVertically(),
                            content = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 16.dp)
                                        .clickable {
                                            onEvent(DashboardScreenEvents.OnSetCheckedTaskVisible(!state.isCheckedTaskVisible))
                                        },
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "See completed task",
                                        fontFamily = nunitoFont
                                    )
                                    Icon(
                                        imageVector = if (state.isCheckedTaskVisible)
                                            Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                        contentDescription = "See completed task"
                                    )
                                }
                            }
                        )
                    }
                    items(completedTasks) { task ->
                        AnimatedVisibility(
                            visible = state.isCheckedTaskVisible && !state.isLoading,
                            enter = expandVertically(),
                            exit = shrinkVertically(),
                            content = {
                                CompletedTaskItem(
                                    task = task,
                                    onCheckChange = {
                                        onEvent(DashboardScreenEvents.OnCheckChange(it))
                                    },
                                    onTaskClick = {
                                        onEvent(DashboardScreenEvents.OnTaskClick(task))
                                    }
                                )
                            }
                        )
                    }
                }
            )

        }
    }
}

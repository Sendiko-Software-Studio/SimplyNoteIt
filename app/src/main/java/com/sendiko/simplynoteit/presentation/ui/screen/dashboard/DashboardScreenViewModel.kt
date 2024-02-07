package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.simplynoteit.data.requests.AddTaskRequest
import com.sendiko.simplynoteit.data.requests.UpdateTaskRequest
import com.sendiko.simplynoteit.data.responses.AddTaskResponse
import com.sendiko.simplynoteit.data.responses.DeleteTaskResponse
import com.sendiko.simplynoteit.data.responses.GetTasksResponse
import com.sendiko.simplynoteit.data.responses.TaskItem
import com.sendiko.simplynoteit.data.responses.UpdateTaskResponse
import com.sendiko.simplynoteit.domain.repositories.TaskRepository
import com.sendiko.simplynoteit.domain.repositories.UserRepository
import com.sendiko.simplynoteit.presentation.ui.helper.FailedRequest
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.SortBy.*
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.TaskAction.Update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardScreenState())
    private val _username = userRepository.getUsername()
    private val _token = userRepository.getToken()
    private val _userId = userRepository.getId()
    private val _sortBy = taskRepository.getSortBy()
    val state =
        combine(
            flow = _username,
            flow2 = _token,
            flow3 = _userId,
            flow4 = _sortBy,
            flow5 = _state
        ) { username, token, userId, sortBy, state ->
            state.copy(
                name = username,
                token = "Bearer $token",
                userId = userId,
                sortBy = enumValueOf(sortBy)
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardScreenState())

    private fun deleteTask(id: String) {
        _state.update {
            it.copy(
                isLoading = true,
                taskSheetState = TaskSheetState(isVisible = false)
            )
        }
        val request = taskRepository.deleteTask(
            id = id,
            token = state.value.token
        )
        request.enqueue(
            object : Callback<DeleteTaskResponse> {
                override fun onResponse(
                    call: Call<DeleteTaskResponse>,
                    response: Response<DeleteTaskResponse>
                ) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            tasks = emptyList(),
                            notificationMessage = "Task deleted! congrats!"
                        )
                    }
                }

                override fun onFailure(call: Call<DeleteTaskResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            isRequestFailed = FailedRequest(
                                isFailed = true,
                                failedMessage = t.message.toString()
                            ),
                            notificationMessage = t.message.toString()
                        )
                    }
                }

            }
        )
    }

    private fun updateTask(task: TaskItem) {
        _state.update {
            it.copy(
                isLoading = true,
                taskSheetState = TaskSheetState(
                    isVisible = false,
                    taskText = it.taskSheetState.taskText,
                    descriptionText = it.taskSheetState.descriptionText
                )
            )
        }
        val request = taskRepository.updateTask(
            id = task.id.toString(),
            token = state.value.token,
            request = UpdateTaskRequest(
                title = task.title,
                description = task.description,
                isDone = task.isDone
            )
        )
        request.enqueue(
            object : Callback<UpdateTaskResponse> {
                override fun onResponse(
                    call: Call<UpdateTaskResponse>,
                    response: Response<UpdateTaskResponse>
                ) {
                    when(response.code()){
                        200 -> _state.update {
                            it.copy(
                                isLoading = false,
                                taskSheetState = TaskSheetState(isVisible = false),
                                tasks = emptyList(),
                                notificationMessage = when (response.body()?.task?.isDone) {
                                    "1" -> "Task completed!"
                                    "0" -> "Oh? missing something?"
                                    else -> ""
                                }
                            )
                        }
                        422 -> _state.update {
                            it.copy(
                                isLoading = false,
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Title and Description cannot be empty."
                                ),
                                notificationMessage = "Title and Description cannot be empty."
                            )
                        }
                        500 -> _state.update {
                            it.copy(
                                isLoading = false,
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                ),
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<UpdateTaskResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestFailed = FailedRequest(
                                isFailed = true,
                                failedMessage = t.message.toString()
                            ),
                            notificationMessage = "Server error."
                        )
                    }
                }

            }
        )
    }

    private fun postTask() {
        _state.update {
            it.copy(
                isLoading = true,
                taskSheetState = TaskSheetState(
                    isVisible = false,
                    taskText = it.taskSheetState.taskText,
                    descriptionText = it.taskSheetState.descriptionText
                )
            )
        }
        val request = taskRepository.postTask(
            state.value.token,
            request = AddTaskRequest(
                isDone = 0,
                title = state.value.taskSheetState.taskText,
                description = state.value.taskSheetState.descriptionText,
                userId = state.value.userId
            )
        )
        request.enqueue(
            object : Callback<AddTaskResponse> {
                override fun onResponse(
                    call: Call<AddTaskResponse>,
                    response: Response<AddTaskResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        201 -> _state.update {
                            it.copy(
                                notificationMessage = "Task successfully added!",
                                taskSheetState = TaskSheetState(isVisible = false),
                                tasks = emptyList(),
                            )
                        }
                        422 -> _state.update {
                            it.copy(
                                isLoading = false,
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Title and Description cannot be empty."
                                ),
                                notificationMessage = "Title and Description cannot be empty."
                            )
                        }
                        else -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                ),
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<AddTaskResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            isRequestFailed = FailedRequest(
                                isFailed = true,
                                failedMessage = t.message.toString()
                            ),
                            isLoading = false,
                            notificationMessage = "Server error."
                        )
                    }
                }
            }
        )
    }

    private fun getTasks() {
        _state.update { it.copy(isLoading = true) }
        val request = taskRepository.getTasks(state.value.token)
        request.enqueue(
            object : Callback<GetTasksResponse> {
                override fun onResponse(
                    call: Call<GetTasksResponse>,
                    response: Response<GetTasksResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> _state.update {
                            response.body()?.task.let { tasks ->
                                it.copy(
                                    tasks = when(state.value.sortBy){
                                        AlphabetDesc -> tasks!!.sortedByDescending { it.title }
                                        AlphabetAsc -> tasks!!.sortedBy { it.title }
                                        ID -> tasks?: emptyList()
                                        DateCreated -> tasks!!.sortedByDescending { it.createdAt }
                                    }
                                )
                            }
                        }

                        else -> _state.update {
                            it.copy(
                                isRequestFailed = FailedRequest(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<GetTasksResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            isRequestFailed = FailedRequest(
                                isFailed = true,
                                failedMessage = t.message.toString()
                            ),
                            isLoading = false
                        )
                    }
                }
            }
        )
    }

    fun onEvent(events: DashboardScreenEvents) {
        when (events) {
            is DashboardScreenEvents.OnSetCheckedTaskVisible -> _state.update {
                it.copy(isCheckedTaskVisible = events.isVisible)
            }
            is DashboardScreenEvents.OnTaskClick -> _state.update {
                it.copy(
                    task = events.task,
                    taskSheetState = TaskSheetState(
                        isVisible = !it.taskSheetState.isVisible,
                        action = Update,
                        taskText = events.task.title,
                        descriptionText = events.task.description
                    ),
                )
            }
            is DashboardScreenEvents.OnTaskDescriptionChange -> _state.update {
                it.copy(
                    taskSheetState = TaskSheetState(
                        descriptionText = events.description,
                        isVisible = true,
                        action = it.taskSheetState.action,
                        taskText = it.taskSheetState.taskText
                    )
                )
            }
            is DashboardScreenEvents.OnTaskTitleChange -> _state.update {
                it.copy(
                    taskSheetState = TaskSheetState(
                        taskText = events.title,
                        isVisible = true,
                        action = it.taskSheetState.action,
                        descriptionText = it.taskSheetState.descriptionText
                    )
                )
            }
            is DashboardScreenEvents.OnTaskSheetVisibilityChanged -> _state.update {
                it.copy(
                    taskSheetState = TaskSheetState(
                        isVisible = events.isVisible,
                        action = events.taskAction
                    )
                )
            }
            DashboardScreenEvents.OnTaskDescClear -> _state.update {
                it.copy(
                    taskSheetState = TaskSheetState(
                        taskText = it.taskSheetState.taskText,
                        descriptionText = "",
                        isVisible = true,
                        action = it.taskSheetState.action,
                    )
                )
            }
            DashboardScreenEvents.OnTaskTitleClear -> _state.update {
                it.copy(
                    taskSheetState = TaskSheetState(
                        taskText = "",
                        descriptionText = it.taskSheetState.descriptionText,
                        isVisible = true,
                        action = it.taskSheetState.action,
                    )
                )
            }
            DashboardScreenEvents.OnNotificationMessageClear -> _state.update {
                it.copy(
                    notificationMessage = ""
                )
            }
            DashboardScreenEvents.OnFailedRequestStateClear -> _state.update {
                it.copy(isRequestFailed = FailedRequest(isFailed = false, failedMessage = ""))
            }
            is DashboardScreenEvents.OnUpdateTask -> {
                val task = TaskItem(
                    id = state.value.task!!.id,
                    title = state.value.taskSheetState.taskText,
                    description = state.value.taskSheetState.descriptionText,
                    isDone = state.value.task!!.isDone,
                    userId = state.value.userId,
                    updatedAt = state.value.task!!.updatedAt,
                    createdAt = state.value.task!!.createdAt
                )
                updateTask(task)
            }
            is DashboardScreenEvents.OnCreateTask -> postTask()
            is DashboardScreenEvents.OnTaskLoad -> getTasks()
            is DashboardScreenEvents.OnCheckChange -> updateTask(events.task)
            is DashboardScreenEvents.OnDeleteTask -> deleteTask(state.value.task!!.id.toString())
            is DashboardScreenEvents.OnSetSortBy -> {
                viewModelScope.launch {
                    taskRepository.setSortBy(events.sortBy)
                }
                _state.update {
                    it.copy(
                        sortBy = events.sortBy,
                        tasks = emptyList()
                    )
                }
            }
        }
    }
}
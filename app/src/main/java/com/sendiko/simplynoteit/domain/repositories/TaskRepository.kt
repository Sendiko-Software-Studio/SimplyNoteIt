package com.sendiko.simplynoteit.domain.repositories

import com.sendiko.simplynoteit.data.ApiService
import com.sendiko.simplynoteit.data.requests.AddTaskRequest
import com.sendiko.simplynoteit.data.requests.UpdateTaskRequest
import javax.inject.Inject

class TaskRepository @Inject constructor(private val client: ApiService) {

    fun getTasks(token: String) = client.getTasks(token)

    fun postTask(token: String, request: AddTaskRequest) = client.postTask(token, request)

    fun updateTask(id: String, token: String, request: UpdateTaskRequest) = client.updateTask(id, token, request)
}
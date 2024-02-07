package com.sendiko.simplynoteit.domain.repositories

import com.sendiko.simplynoteit.data.ApiService
import com.sendiko.simplynoteit.data.requests.AddTaskRequest
import com.sendiko.simplynoteit.data.requests.UpdateTaskRequest
import com.sendiko.simplynoteit.domain.preference.AppPreferences
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.SortBy
import javax.inject.Inject

class TaskRepository @Inject constructor(private val client: ApiService, private val preferences: AppPreferences) {

    fun getTasks(token: String) = client.getTasks(token)

    fun postTask(token: String, request: AddTaskRequest) = client.postTask(token, request)

    fun updateTask(id: String, token: String, request: UpdateTaskRequest) = client.updateTask(id, token, request)

    fun deleteTask(id: String, token: String) = client.deleteTask(id, token)

    fun getSortBy() = preferences.getSortBy()

    suspend fun setSortBy(sortBy: SortBy) = preferences.setSortBy(sortBy.name)
}
package com.sendiko.simplynoteit.dashboard.domain

import com.sendiko.simplynoteit.core.network.ApiService
import com.sendiko.simplynoteit.dashboard.data.AddTaskRequest
import com.sendiko.simplynoteit.dashboard.data.UpdateTaskRequest
import com.sendiko.simplynoteit.core.preference.AppPreferences
import com.sendiko.simplynoteit.core.ui.helper.SortBy
import javax.inject.Inject

class TaskRepository @Inject constructor(private val client: ApiService, private val preferences: AppPreferences) {

    fun getTasks(token: String) = client.getTasks(token)

    fun postTask(token: String, request: AddTaskRequest) = client.postTask(token, request)

    fun updateTask(id: String, token: String, request: UpdateTaskRequest) = client.updateTask(id, token, request)

    fun deleteTask(id: String, token: String) = client.deleteTask(id, token)

    fun getSortBy() = preferences.getSortBy()

    suspend fun setSortBy(sortBy: SortBy) = preferences.setSortBy(sortBy.name)
}
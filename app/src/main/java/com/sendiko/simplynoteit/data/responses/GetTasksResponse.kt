package com.sendiko.simplynoteit.data.responses

data class GetTasksResponse(
	val task: List<TaskItem>,
	val message: String,
	val status: Int
)

data class TaskItem(
	val isDone: Int,
	val updatedAt: String,
	val userId: Int,
	val description: String,
	val createdAt: String,
	val id: Int,
	val title: String
)


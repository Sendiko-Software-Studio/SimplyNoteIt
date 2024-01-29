package com.sendiko.simplynoteit.data.responses

import com.google.gson.annotations.SerializedName

data class AddTaskResponse(

	@field:SerializedName("task")
	val task: Task2,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class Task2(

	@field:SerializedName("is_done")
	val isDone: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)

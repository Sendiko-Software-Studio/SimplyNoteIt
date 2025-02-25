package com.sendiko.simplynoteit.task.dashboard.data

import com.google.gson.annotations.SerializedName

data class AddTaskRequest(

	@field:SerializedName("is_done")
	val isDone: Int,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String
)


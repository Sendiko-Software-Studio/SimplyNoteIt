package com.sendiko.simplynoteit.data.requests

import com.google.gson.annotations.SerializedName

data class AddTaskRequest(

	@field:SerializedName("is_done")
	val isDone: Int,

	@field:SerializedName("user_id")
	val userId: String,
	val description: String,
	val title: String
)


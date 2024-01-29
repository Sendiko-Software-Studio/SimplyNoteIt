package com.sendiko.simplynoteit.data.requests

import com.google.gson.annotations.SerializedName

data class UpdateTaskRequest(

	@field:SerializedName("is_done")
	val isDone: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String
)

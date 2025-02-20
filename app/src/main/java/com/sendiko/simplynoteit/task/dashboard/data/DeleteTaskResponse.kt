package com.sendiko.simplynoteit.task.dashboard.data

import com.google.gson.annotations.SerializedName

data class DeleteTaskResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

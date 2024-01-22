package com.sendiko.simplynoteit.data.responses

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

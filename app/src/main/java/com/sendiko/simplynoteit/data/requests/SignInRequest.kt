package com.sendiko.simplynoteit.data.requests

import com.google.gson.annotations.SerializedName

data class SignInRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

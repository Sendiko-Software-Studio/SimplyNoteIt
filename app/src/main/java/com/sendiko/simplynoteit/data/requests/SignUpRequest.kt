package com.sendiko.simplynoteit.data.requests

import com.google.gson.annotations.SerializedName

data class SignUpRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("password_confirmation")
	val passwordConfirmation: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

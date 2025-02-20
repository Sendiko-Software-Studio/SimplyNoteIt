package com.sendiko.simplynoteit.user.auth.signin.data

import com.google.gson.annotations.SerializedName

data class SignInRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

package com.sendiko.simplynoteit.data.responses

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User1,

	@field:SerializedName("status")
	val status: Int
)

data class User1(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)

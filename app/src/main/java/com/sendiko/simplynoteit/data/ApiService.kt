package com.sendiko.simplynoteit.data

import com.sendiko.simplynoteit.data.requests.SignInRequest
import com.sendiko.simplynoteit.data.requests.SignUpRequest
import com.sendiko.simplynoteit.data.responses.SignInResponse
import com.sendiko.simplynoteit.data.responses.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    fun signUp(
        @Body request: SignUpRequest
    ): Call<SignUpResponse>

    @POST("login")
    fun signIn(
        @Body request: SignInRequest
    ): Call<SignInResponse>

}
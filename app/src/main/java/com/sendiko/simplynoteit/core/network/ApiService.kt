package com.sendiko.simplynoteit.core.network

import com.sendiko.simplynoteit.task.dashboard.data.AddTaskRequest
import com.sendiko.simplynoteit.user.auth.signin.data.SignInRequest
import com.sendiko.simplynoteit.user.auth.signup.data.SignUpRequest
import com.sendiko.simplynoteit.task.dashboard.data.UpdateTaskRequest
import com.sendiko.simplynoteit.task.dashboard.data.AddTaskResponse
import com.sendiko.simplynoteit.task.dashboard.data.DeleteTaskResponse
import com.sendiko.simplynoteit.task.dashboard.data.GetTasksResponse
import com.sendiko.simplynoteit.user.profile.data.GetUserResponse
import com.sendiko.simplynoteit.user.auth.signin.data.SignInResponse
import com.sendiko.simplynoteit.user.profile.data.SignOutResponse
import com.sendiko.simplynoteit.user.auth.signup.data.SignUpResponse
import com.sendiko.simplynoteit.task.dashboard.data.UpdateTaskResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("register")
    fun signUp(
        @Body request: SignUpRequest
    ): Call<SignUpResponse>

    @POST("login")
    fun signIn(
        @Body request: SignInRequest
    ): Call<SignInResponse>

    @GET("task")
    fun getTasks(
        @Header("Authorization") token: String
    ): Call<GetTasksResponse>

    @POST("task")
    fun postTask(
        @Header("Authorization") token: String,
        @Body request: AddTaskRequest
    ): Call<AddTaskResponse>

    @PUT("task/{id}")
    fun updateTask(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Body request: UpdateTaskRequest
    ): Call<UpdateTaskResponse>

    @DELETE("task/{id}")
    fun deleteTask(
        @Path("id") id: String,
        @Header("Authorization") token: String,
    ): Call<DeleteTaskResponse>

    @POST("logout")
    fun signOut(
        @Header("Authorization") token: String
    ): Call<SignOutResponse>

    @GET("user/{id}")
    fun getUser(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Call<GetUserResponse>

}
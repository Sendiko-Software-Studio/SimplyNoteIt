package com.sendiko.simplynoteit.data

import com.sendiko.simplynoteit.data.requests.AddTaskRequest
import com.sendiko.simplynoteit.data.requests.SignInRequest
import com.sendiko.simplynoteit.data.requests.SignUpRequest
import com.sendiko.simplynoteit.data.requests.UpdateTaskRequest
import com.sendiko.simplynoteit.data.responses.AddTaskResponse
import com.sendiko.simplynoteit.data.responses.DeleteTaskResponse
import com.sendiko.simplynoteit.data.responses.GetTasksResponse
import com.sendiko.simplynoteit.data.responses.SignInResponse
import com.sendiko.simplynoteit.data.responses.SignUpResponse
import com.sendiko.simplynoteit.data.responses.UpdateTaskResponse
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

}
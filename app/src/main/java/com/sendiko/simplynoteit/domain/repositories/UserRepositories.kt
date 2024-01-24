package com.sendiko.simplynoteit.domain.repositories

import com.sendiko.simplynoteit.data.ApiService
import com.sendiko.simplynoteit.data.requests.SignInRequest
import com.sendiko.simplynoteit.data.requests.SignUpRequest
import com.sendiko.simplynoteit.domain.preference.AppPreferences
import javax.inject.Inject

class UserRepository @Inject constructor(private val client: ApiService, private val preferences: AppPreferences) {

    fun signUp(request: SignUpRequest) = client.signUp(request)

    fun signIn(request: SignInRequest) = client.signIn(request)

    suspend fun saveUsername(username: String) = preferences.setName(username)

    fun getUsername() = preferences.getName()

    suspend fun setToken(token: String) = preferences.setToken(token)

    fun getToken() = preferences.getToken()
}
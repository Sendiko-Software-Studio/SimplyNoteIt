package com.sendiko.simplynoteit.user.core.domain

import com.sendiko.simplynoteit.core.network.ApiService
import com.sendiko.simplynoteit.user.auth.signin.data.SignInRequest
import com.sendiko.simplynoteit.user.auth.signup.data.SignUpRequest
import com.sendiko.simplynoteit.core.preference.AppPreferences
import javax.inject.Inject

class UserRepository @Inject constructor(private val client: ApiService, private val preferences: AppPreferences) {

    fun signUp(request: SignUpRequest) = client.signUp(request)

    fun signIn(request: SignInRequest) = client.signIn(request)

    suspend fun saveUsername(username: String) = preferences.setName(username)

    fun getUsername() = preferences.getName()

    suspend fun setToken(token: String) = preferences.setToken(token)

    fun getToken() = preferences.getToken()

    suspend fun setId(id: String) = preferences.setId(id)

    fun getId() = preferences.getId()

    fun signOut(token: String) = client.signOut(token)

    fun getUser(userId: String, token: String) = client.getUser(userId, token)
}
package com.sendiko.simplynoteit.domain.repositories

import com.sendiko.simplynoteit.data.ApiService
import com.sendiko.simplynoteit.data.requests.SignInRequest
import com.sendiko.simplynoteit.data.requests.SignUpRequest
import javax.inject.Inject

class UserRepository @Inject constructor(private val client: ApiService) {

    fun signUp(request: SignUpRequest) = client.signUp(request)

    fun signIn(request: SignInRequest) = client.signIn(request)
}
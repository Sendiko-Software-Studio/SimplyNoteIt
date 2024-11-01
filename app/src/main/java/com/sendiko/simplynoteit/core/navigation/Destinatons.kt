package com.sendiko.simplynoteit.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object WelcomeScreen

@Serializable
object SignInScreen

@Serializable
object SignUpScreen

@Serializable
object MainGraph

@Serializable
object DashboardScreen

@Serializable
data class ProfileScreen(val userId: String)
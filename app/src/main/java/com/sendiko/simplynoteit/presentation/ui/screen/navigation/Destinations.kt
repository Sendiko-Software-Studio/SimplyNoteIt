package com.sendiko.simplynoteit.presentation.ui.screen.navigation

sealed class Destinations(val destination: String) {

    data object WelcomeScreenDestination: Destinations("welcome_screen")
    data object SignInScreenDestination: Destinations("signin_screen")
    data object SignUpScreenDestination: Destinations("signup_screen")
}
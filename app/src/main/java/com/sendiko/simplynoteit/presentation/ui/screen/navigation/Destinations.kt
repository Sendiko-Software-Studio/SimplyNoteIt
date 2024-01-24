package com.sendiko.simplynoteit.presentation.ui.screen.navigation

sealed class Destinations(val destination: String) {

    data object SplashScreenDestiation: Destinations("splash_screen")

    data object WelcomeScreenDestination: Destinations("welcome_screen")
    data object SignInScreenDestination: Destinations("signin_screen")
    data object SignUpScreenDestination: Destinations("signup_screen")
    data object MainGraph: Destinations("main_graph")
    data object DashboardScreenDestination: Destinations("dashboard_screen")
}
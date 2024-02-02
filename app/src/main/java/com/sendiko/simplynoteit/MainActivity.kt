package com.sendiko.simplynoteit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sendiko.simplynoteit.presentation.ui.screen.WelcomeScreen
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.DashboardScreen
import com.sendiko.simplynoteit.presentation.ui.screen.dashboard.DashboardScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import com.sendiko.simplynoteit.presentation.ui.screen.profile.ProfileScreen
import com.sendiko.simplynoteit.presentation.ui.screen.profile.ProfileScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.screen.signin.SignInScreen
import com.sendiko.simplynoteit.presentation.ui.screen.signin.SignInScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.screen.signup.SignUpScreen
import com.sendiko.simplynoteit.presentation.ui.screen.signup.SignUpScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.screen.splash.SplashScreen
import com.sendiko.simplynoteit.presentation.ui.screen.splash.SplashScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.theme.SimplyNoteItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SimplyNoteItTheme {
                NavHost(
                    navController = navController,
                    startDestination = Destinations.SplashScreenDestiation.destination,
                    builder = {
                        composable(
                            route = Destinations.SplashScreenDestiation.destination,
                            content = {
                                val viewModel: SplashScreenViewModel = hiltViewModel()
                                SplashScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onNavigate = {
                                        navController.navigate(
                                            route = it
                                        ) {
                                            popUpTo(
                                                navController.graph.id,
                                            ) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destinations.WelcomeScreenDestination.destination,
                            content = {
                                WelcomeScreen { destination ->
                                    navController.navigate(destination)
                                }
                            }
                        )
                        composable(
                            route = Destinations.SignInScreenDestination.destination,
                            content = {
                                val viewModel: SignInScreenViewModel = hiltViewModel()
                                SignInScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvents = viewModel::onEvent,
                                    onNavigate = {
                                        navController.navigate(it)
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destinations.SignUpScreenDestination.destination,
                            content = {
                                val viewModel: SignUpScreenViewModel = hiltViewModel()
                                SignUpScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvents = viewModel::onEvent,
                                    onNavigate = {
                                        navController.navigate(it)
                                    }
                                )
                            }
                        )
                        navigation(
                            startDestination = Destinations.DashboardScreenDestination.destination,
                            route = Destinations.MainGraph.destination,
                            builder = {
                                composable(
                                    route = Destinations.DashboardScreenDestination.destination,
                                    content = {
                                        val viewModel: DashboardScreenViewModel = hiltViewModel()
                                        DashboardScreen(
                                            state = viewModel.state.collectAsState().value,
                                            onEvent = viewModel::onEvent,
                                            onNavigate = {
                                                navController.navigate(it)
                                            }
                                        )
                                    }
                                )
                                composable(
                                    route = Destinations.ProfileScreenDestination.destination,
                                    content = {
                                        val viewModel: ProfileScreenViewModel = hiltViewModel()
                                        ProfileScreen(
                                            state = viewModel.state.collectAsState().value,
                                            onEvent = viewModel::onEvent,
                                            onNavigate = {
                                                navController.navigate(it)
                                            }
                                        )
                                    }
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}

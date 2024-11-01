package com.sendiko.simplynoteit.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sendiko.simplynoteit.welcome.presentation.WelcomeScreen
import com.sendiko.simplynoteit.dashboard.presentation.DashboardScreen
import com.sendiko.simplynoteit.dashboard.presentation.DashboardScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import com.sendiko.simplynoteit.profile.presentation.ProfileScreen
import com.sendiko.simplynoteit.profile.presentation.ProfileScreenViewModel
import com.sendiko.simplynoteit.signin.presentation.SignInScreen
import com.sendiko.simplynoteit.signin.presentation.SignInScreenViewModel
import com.sendiko.simplynoteit.signup.presentation.SignUpScreen
import com.sendiko.simplynoteit.signup.presentation.SignUpScreenViewModel
import com.sendiko.simplynoteit.splash.presentation.SplashScreen
import com.sendiko.simplynoteit.splash.presentation.SplashScreenViewModel

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.SplashScreenDestiation.destination,
        builder = {
            composable<SplashScreen> {
                val viewModel: SplashScreenViewModel = hiltViewModel()
                val state = viewModel.state.collectAsStateWithLifecycle().value
                SplashScreen(
                    state = state,
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
            composable<WelcomeScreen> {
                WelcomeScreen { destination ->
                    navController.navigate(destination)
                }
            }
            composable<SignInScreen> {
                val viewModel: SignInScreenViewModel = hiltViewModel()
                val state = viewModel.state.collectAsStateWithLifecycle().value
                SignInScreen(
                    state = state,
                    onEvents = viewModel::onEvent,
                    onNavigate = {
                        navController.navigate(it)
                    }
                )
            }
            composable<SignUpScreen> {
                val viewModel: SignUpScreenViewModel = hiltViewModel()
                val state = viewModel.state.collectAsStateWithLifecycle().value
                SignUpScreen(
                    state = state,
                    onEvents = viewModel::onEvent,
                    onNavigate = {
                        navController.navigate(it)
                    }
                )
            }
            navigation<MainGraph> (
                startDestination = DashboardScreen,
                builder = {
                    composable<DashboardScreen> {
                        val viewModel: DashboardScreenViewModel = hiltViewModel()
                        val state = viewModel.state.collectAsStateWithLifecycle().value
                        DashboardScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            onNavigate = {
                                navController.navigate(it)
                            }
                        )
                    }
                    composable<ProfileScreen> {
                        val viewModel: ProfileScreenViewModel = hiltViewModel()
                        val state = viewModel.state.collectAsStateWithLifecycle().value
                        ProfileScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            onNavigate = {
                                navController.navigate(it)
                            }
                        )
                    }
                }
            )
        }
    )
}
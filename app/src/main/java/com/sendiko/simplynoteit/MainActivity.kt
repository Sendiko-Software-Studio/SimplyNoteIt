package com.sendiko.simplynoteit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.simplynoteit.presentation.ui.screen.WelcomeScreen
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import com.sendiko.simplynoteit.presentation.ui.screen.signin.SignInScreen
import com.sendiko.simplynoteit.presentation.ui.screen.signin.SignInScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.screen.signup.SignUpScreen
import com.sendiko.simplynoteit.presentation.ui.screen.signup.SignUpScreenViewModel
import com.sendiko.simplynoteit.presentation.ui.theme.SimplyNoteItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SimplyNoteItTheme {
                NavHost(
                    navController = navController,
                    startDestination = Destinations.WelcomeScreenDestination.destination,
                    builder = {
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
                                val viewModel: SignUpScreenViewModel = viewModel()
                                SignUpScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvents = viewModel::onEvent,
                                    onNavigate = {
                                        navController.navigate(it)
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
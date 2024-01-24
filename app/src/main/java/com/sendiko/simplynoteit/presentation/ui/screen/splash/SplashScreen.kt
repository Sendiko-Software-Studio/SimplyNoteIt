package com.sendiko.simplynoteit.presentation.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sendiko.simplynoteit.R
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    state: SplashScreenState,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(
        key1 = state.isLoggedIn,
        block = {
            delay(1000)
            if (state.isLoggedIn)
                onNavigate(Destinations.MainGraph.destination)
            else onNavigate(Destinations.WelcomeScreenDestination.destination)
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
        content = {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            CircularProgressIndicator(
                modifier = Modifier.size(128.dp)
            )
        }
    )
}
package com.sendiko.simplynoteit.presentation.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.R
import com.sendiko.simplynoteit.presentation.ui.components.ContentBoxWithNotification
import com.sendiko.simplynoteit.presentation.ui.components.FilledButton
import com.sendiko.simplynoteit.presentation.ui.components.RedButton
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    onEvent: (ProfileScreenEvent) -> Unit,
    onNavigate: (String) -> Unit,
) {
    LaunchedEffect(
        key1 = state.isSignedOutSuccessfully,
        key2 = state.name,
        block = {
            if (state.isSignedOutSuccessfully)
                onNavigate(Destinations.SignInScreenDestination.destination)

            if (state.name.isBlank())
                onEvent(ProfileScreenEvent.OnGetUserInfo)
        }
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Your Profile",
                        fontFamily = nunitoFont,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigate(Destinations.DashboardScreenDestination.destination) },
                        content = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "navigate back"
                            )
                        }
                    )
                }
            )
        }
    ) {
        ContentBoxWithNotification(
            message = state.notificationMessage,
            isLoading = state.isLoading,
            isErrorNotification = state.isRequestFailed.isFailed
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding()
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Username: ",
                            fontFamily = nunitoFont,
                            fontSize = 16.sp
                        )
                        Text(
                            text = state.name,
                            fontFamily = nunitoFont,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Card {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Email: ",
                            fontFamily = nunitoFont,
                            fontSize = 16.sp
                        )
                        Text(
                            text = state.email,
                            fontFamily = nunitoFont,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    RedButton(
                        text = "Logout",
                        onClick = {
                            onEvent(ProfileScreenEvent.OnLogoutClick)
                        },
                        modifier = Modifier.weight(1f)
                    )
                    FilledButton(
                        text = "Change password",
                        onClick = { /*TODO*/ },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Card {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "About us",
                            fontFamily = nunitoFont,
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "info"
                        )
                    }
                }
                Card {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Privacy and policy",
                            fontFamily = nunitoFont,
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Default.PrivacyTip,
                            contentDescription = "privacy and policy info"
                        )
                    }
                }
                Card {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Contact us",
                            fontFamily = nunitoFont,
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Default.ContactPage,
                            contentDescription = "contact"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "powered by:",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = nunitoFont
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.logo_long),
                        contentDescription = "logo",
                        modifier = Modifier.weight(3f)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPrev() {
    ProfileScreen(
        state = ProfileScreenState(),
        onEvent = {},
        onNavigate = {

        }
    )
}
package com.sendiko.simplynoteit.presentation.ui.screen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.R
import com.sendiko.simplynoteit.presentation.ui.components.FilledButton
import com.sendiko.simplynoteit.presentation.ui.components.Notification
import com.sendiko.simplynoteit.presentation.ui.components.OutlinedTextField
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(
    state: SignInScreenState,
    onEvents: (SignInScreenEvents) -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold { paddingValues ->
        LaunchedEffect(
            key1 = state.isRequestFailed.failedMessage,
            block = {
                if (state.isRequestFailed.failedMessage.isNotBlank()){
                    delay(2000)
                    onEvents(SignInScreenEvents.SetFailedMessage(!state.isRequestFailed.isFailed))
                }
            }
        )
        Box{
            Column(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                Box(
                    modifier = Modifier.weight(3f),
                    contentAlignment = Alignment.TopCenter,
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.signin),
                            contentDescription = "sign in",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                )
                Column(
                    modifier = Modifier.weight(4f)
                ) {
                    Text(
                        text = "Sign In to your account",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = nunitoFont,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = state.emailText,
                        onNewValue = {
                            onEvents(SignInScreenEvents.OnEmailChanged(it))
                        },
                        label = "Email",
                        hint = "your email",
                        leadingIcon = Icons.Default.Person,
                        onClearValue = { onEvents(SignInScreenEvents.OnEmailCleared) },
                        isPasswordTextField = false,
                        keyboardType = KeyboardType.Email,
                        isPasswordVisible = true
                    )
                    OutlinedTextField(
                        value = state.passwordText,
                        onNewValue = {
                            onEvents(SignInScreenEvents.OnPasswordChanged(it))
                        },
                        label = "Password",
                        hint = "your password",
                        leadingIcon = Icons.Default.Lock,
                        onClearValue = { onEvents(SignInScreenEvents.OnPasswordCleared) },
                        isPasswordTextField = true,
                        isPasswordVisible = state.isPasswordVisible,
                        onPasswordVisibilityToggle = {
                            onEvents(SignInScreenEvents.OnPasswordVisibilityChanged(!state.isPasswordVisible))
                        },
                        keyboardType = KeyboardType.Password,
                    )
                    FilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (state.isLoading) "Loading..." else  "Sign in",
                        onClick = {
                            onEvents(
                                SignInScreenEvents.OnSignIn(
                                    username = state.emailText,
                                    password = state.passwordText
                                )
                            )
                        },
                        enabled = !state.isLoading
                    )
                    TextButton(
                        onClick = { onNavigate(Destinations.SignUpScreenDestination.destination) },
                        content = {
                            Text(
                                text = "Belum punya akun? daftar sini!",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontFamily = nunitoFont
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Notification(
                message = state.isRequestFailed.failedMessage,
                isVisible = state.isRequestFailed.failedMessage.isNotBlank()
            )
        }
    }
}
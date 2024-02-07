package com.sendiko.simplynoteit.presentation.ui.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import com.sendiko.simplynoteit.presentation.ui.components.ContentBoxWithNotification
import com.sendiko.simplynoteit.presentation.ui.components.FilledButton
import com.sendiko.simplynoteit.presentation.ui.components.OutlinedTextField
import com.sendiko.simplynoteit.presentation.ui.screen.navigation.Destinations
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(
    state: SignUpScreenState,
    onEvents: (SignUpScreenEvent) -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold { paddingValues ->
        LaunchedEffect(
            key1 = state.isRequestFailed.failedMessage,
            key2 = state.isSignedUpSuccess,
            block = {
                if (state.isRequestFailed.failedMessage.isNotBlank()){
                    delay(2000)
                    onEvents(SignUpScreenEvent.SetFailedMessage(!state.isRequestFailed.isFailed))
                }
                if (state.isSignedUpSuccess)
                    onNavigate(Destinations.SignInScreenDestination.destination)
            }
        )
        ContentBoxWithNotification(
            message = state.notificationMessage,
            isErrorNotification = state.isRequestFailed.isFailed,
            isLoading = state.isLoading,
            content = {
                Column(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 16.dp,
                        end = 16.dp
                    )
                ) {
                    Box(
                        modifier = Modifier.weight(3f),
                        contentAlignment = Alignment.Center,
                        content = {
                            Image(
                                painter = painterResource(id = R.drawable.signin),
                                contentDescription = "sign in",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    )
                    Column(
                        modifier = Modifier.weight(7f)
                    ) {
                        Text(
                            text = "Sign Up a new account",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = nunitoFont,
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                        OutlinedTextField(
                            value = state.usernameText,
                            onNewValue = {
                                onEvents(SignUpScreenEvent.OnUsernameChanged(it))
                            },
                            label = "Username",
                            hint = "your username",
                            leadingIcon = Icons.Default.Person,
                            onClearValue = { onEvents(SignUpScreenEvent.OnUsernameCleared) },
                            isPasswordTextField = false,
                            isPasswordVisible = true,
                            isError = state.isUsernameFieldError.isError,
                            errorMessage = state.isUsernameFieldError.errorMessage
                        )

                        OutlinedTextField(
                            value = state.emailText,
                            onNewValue = {
                                onEvents(SignUpScreenEvent.OnEmailChanged(it))
                            },
                            label = "Email",
                            hint = "your email",
                            leadingIcon = Icons.Default.Email,
                            onClearValue = { onEvents(SignUpScreenEvent.OnEmailCleared) },
                            isPasswordTextField = false,
                            keyboardType = KeyboardType.Email,
                            isPasswordVisible = true,
                            isError = state.isEmailTextFieldError.isError,
                            errorMessage = state.isEmailTextFieldError.errorMessage
                        )
                        OutlinedTextField(
                            value = state.passwordText,
                            onNewValue = {
                                onEvents(SignUpScreenEvent.OnPasswordChanged(it))
                            },
                            label = "Password",
                            hint = "your password",
                            leadingIcon = Icons.Default.Lock,
                            onClearValue = { onEvents(SignUpScreenEvent.OnPasswordCleared) },
                            isPasswordTextField = true,
                            isPasswordVisible = state.isPasswordVisible,
                            onPasswordVisibilityToggle = {
                                onEvents(SignUpScreenEvent.OnPasswordVisibilityChanged(!state.isPasswordVisible))
                            },
                            keyboardType = KeyboardType.Password,
                            isError = state.isPasswordFieldError.isError,
                            errorMessage = state.isPasswordFieldError.errorMessage
                        )
                        FilledButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = if (state.isLoading) "Loading..." else "Sign up",
                            enabled = !state.isLoading,
                            onClick = { onEvents(
                                SignUpScreenEvent.OnSignUp(
                                    username = state.usernameText,
                                    password = state.passwordText
                                )) }
                        )
                        TextButton(
                            onClick = { onNavigate(Destinations.SignInScreenDestination.destination) },
                            content = {
                                Text(
                                    text = "Udah punya akun? sign in sini!",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontFamily = nunitoFont
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        )
    }
}
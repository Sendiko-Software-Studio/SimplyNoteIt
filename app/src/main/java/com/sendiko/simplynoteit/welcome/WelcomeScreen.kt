package com.sendiko.simplynoteit.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.R
import com.sendiko.simplynoteit.core.navigation.Destinations
import com.sendiko.simplynoteit.core.ui.components.ButtonSize
import com.sendiko.simplynoteit.core.ui.components.FilledButton
import com.sendiko.simplynoteit.core.ui.theme.nunitoFont

@Composable
fun WelcomeScreen(
    onNavigate: (String) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.add_tasks),
                        contentDescription = null
                    )
                }
            )
            Column(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.welcome_1),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitoFont,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = stringResource(R.string.welcome_2),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = nunitoFont,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(24.dp))
                FilledButton(
                    text = stringResource(R.string.lets_go),
                    onClick = { onNavigate(Destinations.SignInScreenDestination.destination) },
                    buttonSize = ButtonSize.Big,
                )
            }
        }
    }
}
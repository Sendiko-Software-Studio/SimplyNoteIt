package com.sendiko.simplynoteit.presentation.ui.screen.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sendiko.simplynoteit.presentation.ui.components.ContentBoxWithNotification
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardScreenState,
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        "Hellow, " + state.name,
                        fontFamily = nunitoFont,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {

                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        content = {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add tasks")
                        }
                    )
                }
            )
        }
    ) {
        ContentBoxWithNotification(message = state.notificationMessage, isVisible = false) {
            LazyColumn(
                contentPadding = PaddingValues(
                    top = it.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
                content = {
                    
                }
            )
        }
    }
}
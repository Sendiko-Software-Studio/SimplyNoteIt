package com.sendiko.simplynoteit.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

@Composable
fun InfoCard(
    label: String,
    text: String,
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                fontFamily = nunitoFont,
                fontSize = 16.sp
            )
            Text(
                text = text,
                fontFamily = nunitoFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
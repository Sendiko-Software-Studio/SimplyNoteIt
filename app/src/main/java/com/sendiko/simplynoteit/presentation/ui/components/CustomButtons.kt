package com.sendiko.simplynoteit.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

/**
 *
 * Sendiko's Custom FilledButton
 *
 * */
@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    buttonSize: ButtonSize = ButtonSize.Regular,
    enabled: Boolean = true
) {
    if (buttonSize == ButtonSize.Big){
        Button(
            onClick = onClick,
            content = {
                Text(
                    text = text,
                    fontFamily = nunitoFont,
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            modifier = modifier,
            enabled = enabled
        )
    } else {
        Button(
            onClick = onClick,
            content = {
                Text(text = text, fontFamily = nunitoFont)
            },
            modifier = modifier,
            enabled = enabled
        )
    }
}
package com.sendiko.simplynoteit.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCardWithIcon(
    label: String,
    icon: ImageVector,
    extraInfo: String = "",
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontFamily = nunitoFont,
            fontSize = 16.sp
        )
        Icon(
            imageVector = icon,
            contentDescription = "info"
        )
    }
    AnimatedVisibility(
        visible = expanded && extraInfo.isNotBlank(),
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Text(text = extraInfo, modifier = Modifier. padding(8.dp), fontFamily = nunitoFont)
    }
}

const val ABOUT_US = "Sendiko Software Studio is a small company found by Sendiko, with the intention to make high quality softwares accessible to anyone."
const val CONTACT_ME = "If you had any question or issues you can always reach me at rizkysendiko7@gmail.com or file an issue here: https://github.com/Sendiko/SimplyNoteIt/issues, i'll help you with your issues and/or questions!"
const val PRIVACY_POLICY =
    "Data Collection" +
            "\nSimplyNoteIt collect only email from the users, and we ensure the security of the data that we collect. We do not track user behavior, nor do we use cookies or any other tracking technology. The only data that we collect is user email for user's account. This data is stored remotely on secure server." +
            "\n" +
            "\nData Protection" +
            "\nWe take the privacy and security of our users very seriously, and we use industry-standard measures to protect the data that is stored on our server."



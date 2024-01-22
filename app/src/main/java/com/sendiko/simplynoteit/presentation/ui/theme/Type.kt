package com.sendiko.simplynoteit.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.R

val nunitoFont = FontFamily(
    Font(
        resId = R.font.nunito_black,
        weight = FontWeight.Black
    ),
    Font(
        resId = R.font.nunito_blackitalic,
        style = FontStyle.Italic,
        weight = FontWeight.Black
    ),
    Font(
        resId = R.font.nunito_extrabold,
        weight = FontWeight.ExtraBold
    ),
    Font(
        resId = R.font.nunito_extrabolditalic,
        style = FontStyle.Italic,
        weight = FontWeight.ExtraBold
    ),
    Font(
        resId = R.font.nunito_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.nunito_bolditalic,
        style = FontStyle.Italic,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.nunito_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.nunito_semibolditalic,
        style = FontStyle.Italic,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.nunito_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.nunito_mediumitalic,
        style = FontStyle.Italic,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.nunito_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.nunito_regular,
        style = FontStyle.Italic,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.nunito_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.nunito_lightitalic,
        style = FontStyle.Italic,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.nunito_extralight,
        weight = FontWeight.ExtraLight
    ),
    Font(
        resId = R.font.nunito_extralightitalic,
        style = FontStyle.Italic,
        weight = FontWeight.ExtraLight
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
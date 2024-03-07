package com.example.actors.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.actors.R

// Set of Material typography styles to start with
private val MontserratAlternates = FontFamily(
    Font(R.font.montserrat_alternates_light, FontWeight.Light),
    Font(R.font.montserrat_alternates_regular, FontWeight.Normal),
    Font(R.font.montserrat_alternates_medium, FontWeight.Medium),
    Font(R.font.montserrat_alternates_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_alternates_bold, FontWeight.Bold),
)

// Material typography styles
val Typography = Typography(

    headlineLarge = TextStyle( //H4
        fontFamily = MontserratAlternates,
        fontWeight = FontWeight.W700,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle( //H5
        fontFamily = MontserratAlternates,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle( //H6
        fontFamily = MontserratAlternates,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle( //SUBTITLE1
        fontFamily = MontserratAlternates,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle( //SUBTITLE2
        fontFamily = MontserratAlternates,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle( //BODY2
        fontFamily = MontserratAlternates,
        fontSize = 14.sp
    )
)
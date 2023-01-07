package com.example.cardbinapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val customBlack = Color(0xFF000000)
val customGray = Color(0xFF1C212D)
val customWhite = Color(0xFFE9E3E3)
val customRed = Color(0xFFEF4646)

val primaryColor = Color(0xff76a9ff)
val primaryColorLighter = Color(0xFF8BB4FA)
val primaryColorDarker = Color(0xFF6F9EEE)
val secondaryColor = Color(0xffd8e6ff)
val textFieldColor = Color(0xFFF0F7F7)

val darkThemeCreditCardColorList = listOf<Color>(Color(0xFF10435B), Color(0xFF145E80))
val creditCardColorList = listOf<Color>(Color(0xFF2A8680), Color(0xff5CC9C1))

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray

val Colors.activeIndicatorColor
    @Composable
    get() = if (isLight) primaryColor else Purple700

val Colors.inactiveIndicatorColor
    @Composable
    get() = if (isLight) customWhite else LightGray

val Colors.buttonBgColor
    @Composable
    get() = if (isLight) primaryColor else customBlack

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) customWhite else customWhite

val Colors.topAppBarBg: Color
    @Composable
    get() = if (isLight) primaryColor else customBlack

val Colors.appBgColor: Color
    @Composable
    get() = if (isLight) secondaryColor else customGray

val Colors.contentTextColor: Color
    @Composable
    get() = if (isLight) primaryColor else primaryColor
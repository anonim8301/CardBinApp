package com.example.cardbinapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Black = Color(0xFF000000)

val primaryColor = Color(0xff76a9ff)
val primaryColorLighter = Color(0xFF8BB4FA)
val primaryColorDarker = Color(0xFF6F9EEE)
val secondaryColor = Color(0xffd8e6ff)
val creditCardColor1 = Color(0xFF2A8680)
val creditCardColor2 = Color(0xff5CC9C1)
val textFieldColor = Color(0xFFF0F7F7)

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray

val Colors.activeIndicatorColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.inactiveIndicatorColor
    @Composable
    get() = if (isLight) LightGray else LightGray

val Colors.buttonBgColor
    @Composable
    get() = if (isLight) primaryColor else primaryColor

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) White else White

val Colors.topAppBarBg: Color
    @Composable
    get() = if (isLight) primaryColor else primaryColor

val Colors.appBgColor: Color
    @Composable
    get() = if (isLight) secondaryColor else secondaryColor

package com.example.cardbinapp.ui.screens.splash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cardbinapp.R
import com.example.cardbinapp.ui.theme.customBlack
import com.example.cardbinapp.ui.theme.primaryColorDarker
import com.example.cardbinapp.ui.theme.primaryColorLighter
import com.example.cardbinapp.utils.Constants.SPLASH_ANIMATION_DURATION
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun SplashScreen(
    navigateToHomeScreen: () -> Unit,
) {

    var visible by remember { mutableStateOf(false) }

    val alphaState by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(SPLASH_ANIMATION_DURATION)
    )

    LaunchedEffect(key1 = true) {
        visible = true
        delay(SPLASH_ANIMATION_DURATION.toLong())
        navigateToHomeScreen()
    }
    Splash(alphaState)
}

@ExperimentalAnimationApi
@Composable
fun Splash(
    alphaState: Float,
) {

    Box(modifier = Modifier
        .background(Brush.verticalGradient(splashBgColor()))
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.alpha(alphaState),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}

@Composable
fun splashBgColor(): List<Color> {
    return if (isSystemInDarkTheme()) listOf(customBlack, customBlack)
    else listOf(primaryColorDarker, primaryColorLighter)
}

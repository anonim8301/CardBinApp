package com.example.cardbinapp.ui.screens.welcome

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.cardbinapp.ui.viewmodels.SharedViewModel
import com.example.cardbinapp.R
import com.example.cardbinapp.domain.model.OnBoardingPage
import com.example.cardbinapp.ui.theme.*
import com.example.cardbinapp.utils.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(sharedViewModel: SharedViewModel, navigateToHomeScreen: () -> Unit) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
    )
    val pageState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.appBgColor)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = ON_BOARDING_PAGE_COUNT,
            state = pageState,
            verticalAlignment = Alignment.Top
        ) { page ->
            PagerScreen(onBoardingPage = pages[page])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(CenterHorizontally),
            pagerState = pageState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            spacing = PAGING_INDICATOR_SPACING,
            indicatorWidth = PAGING_INDICATOR_WIDTH
        )
        FinishButton(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = LARGE_PADDING),
            pageState = pageState,
            onClick = {
                navigateToHomeScreen()
                sharedViewModel.saveOnBoardingState(completed = true)
            }
        )
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.onBoarding_image)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pageState: PagerState,
    onClick: () -> Unit,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(
            visible = pageState.currentPage == pageState.pageCount - 1,
            modifier = Modifier.fillMaxWidth(),
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(initialAlpha = 0f),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(targetAlpha = 0f)

        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBgColor,
                    contentColor = Color.White,
                )) {
                Text(text = stringResource(R.string.welcome_paging_finish_btn))
            }
        }
    }
}

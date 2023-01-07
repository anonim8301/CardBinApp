package com.example.cardbinapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.borutoapp.utils.CardNumberParser
import com.example.cardbinapp.R
import com.example.cardbinapp.ui.theme.*

@Composable
fun CreditCard(
    bankName: String,
    imageId: Int,
    cardBin: String,
    modifier: Modifier,
) {

    Card(
        shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE_DP),
        modifier = modifier
    ) {
        ConstraintLayout(modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = if (isSystemInDarkTheme()) darkThemeCreditCardColorList else creditCardColorList
                )
            )
            .padding(SMALL_PADDING)) {
            val (
                iChip,
                lCardNumber,
                lBankName,
                lExpDate,
                lCardHolder,
                iLogo,
            ) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(lBankName) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end, margin = MEDIUM_PADDING)
                    }
                    .padding(start = MEDIUM_PADDING),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 22.sp,
                color = Color.White,
                text = bankName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Image(
                painter = painterResource(id = R.drawable.chip_credit_card),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(iChip) {
                        top.linkTo(lBankName.bottom, margin = MEDIUM_PADDING)
                        start.linkTo(parent.start, margin = 25.dp)
                    }
                    .size(40.dp)
            )
            CardNumberBlock(
                CardNumberParser(cardBin.filter { !it.isWhitespace() }),
                modifier = Modifier.constrainAs(lCardNumber) {
                    top.linkTo(iChip.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                modifier = Modifier.constrainAs(lExpDate) {
                    top.linkTo(lCardNumber.bottom, margin = SMALL_PADDING)
                    start.linkTo(parent.start, margin = SMALL_PADDING)
                    end.linkTo(parent.end)
                },
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp,
                color = Color.White,
                text = "xx/xx"
            )

            Text(
                modifier = Modifier.constrainAs(lCardHolder) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = LARGE_PADDING)
                },
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp,
                color = Color.White,
                text = "CARD HOLDER"
            )

            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(iLogo) {
                        top.linkTo(lExpDate.bottom)
                        end.linkTo(parent.end, margin = SMALL_PADDING)
                    }
                    .size(50.dp)
            )

        }

    }
}

@Composable
private fun CardNumberBlock(cardNumber: CardNumberParser, modifier: Modifier) {
    Text(
        modifier = modifier,
        fontWeight = FontWeight.Light,
        fontFamily = FontFamily.Monospace,
        fontSize = 25.sp,
        color = Color.White,
        text = "${cardNumber.first} ${cardNumber.second} ${cardNumber.third} ${cardNumber.fourth}"
    )
}
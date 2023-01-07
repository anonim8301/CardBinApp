package com.example.cardbinapp.ui.screens.result

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cardbinapp.R
import com.example.cardbinapp.components.CreditCard
import com.example.cardbinapp.domain.model.Bank
import com.example.cardbinapp.domain.model.CardModel
import com.example.cardbinapp.ui.theme.*

@Composable
fun ResultContent(
    currentCard: CardModel?,
    context: Context,
) {
    if (currentCard != null) CardIsNotNull(currentCard, context) else CardIsNull()
}

@Composable
fun CardIsNull() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.appBgColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = handleBadResponseIcon()),
            contentDescription = ""
        )
        Text(
            text = "Bad Response!",
            fontFamily = FontFamily.Monospace,
            fontSize = 24.sp,
            color = MaterialTheme.colors.contentTextColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun handleBadResponseIcon(): Int {
    return if (isSystemInDarkTheme()) R.drawable.ic_network_error
    else R.drawable.ic_network_error_black
}

@Composable
fun CardIsNotNull(currentCard: CardModel, context: Context) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.appBgColor)
        .padding(LARGE_PADDING)) {
        val (
            creditCard,
            textFields,
        ) = createRefs()

        CreditCard(
            bankName = handleBankName(currentCard.bank),
            imageId = handleImage(scheme = currentCard.scheme),
            cardBin = currentCard.number.cardBin ?: "",
            modifier = Modifier

                .fillMaxWidth()
                .constrainAs(creditCard) {
                    top.linkTo(parent.top)
                },
        )
        TextsSection(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textFields) {
                    top.linkTo(creditCard.bottom, margin = SMALL_PADDING)
                },
            currentCard = currentCard,
            context = context
        )
    }
}

@Composable
fun TextsSection(
    currentCard: CardModel,
    modifier: Modifier,
    context: Context,
) {


    Column(modifier = modifier) {
        TextModel(text = "Scheme: ${currentCard.scheme}")
        TextModel(text = "Type: ${currentCard.type}")
        TextModel(text = "Brand: ${currentCard.brand}")
        TextModel(text = "Prepaid: ${currentCard.prepaid}")
        TextModel(text = "Number length: ${currentCard.number.length}")
        TextModel(text = "Luhn: ${currentCard.number.luhn}")
        HandleBankUrl(context = context, bankUrl = currentCard.bank.url)
        HandleBankPhoneNum(context = context, phoneNum = currentCard.bank.phone)
        TextModel(text = "Bank city: ${currentCard.bank.city}")
        TextModel(text = "Country: ${currentCard.country.name} ${currentCard.country.emoji}")
        TextModel(text = "Country currency: ${currentCard.country.currency}")
        TextModel(text = "Country code(ISO): ${currentCard.country.numeric}")
        HandleCountryLocation(
            context = context,
            latitude = currentCard.country.latitude,
            longitude = currentCard.country.longitude
        )
    }
}

@Composable
fun HandleBankPhoneNum(
    context: Context,
    phoneNum: String,
) {
    val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
    if (phoneNum.isNotBlank()) {
        Text(
            modifier = Modifier
                .padding(top = EXTRA_SMALL_PADDING)
                .clickable { context.startActivity(callIntent) },
            text = "Bank phone: $phoneNum",
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            color = MaterialTheme.colors.contentTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun HandleBankUrl(
    context: Context,
    bankUrl: String?,
) {
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$bankUrl"))
    if (!bankUrl.isNullOrBlank()) {
        Text(
            modifier = Modifier
                .padding(top = EXTRA_SMALL_PADDING)
                .clickable { context.startActivity(webIntent) },
            text = "Bank website: $bankUrl",
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            color = MaterialTheme.colors.contentTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun HandleCountryLocation(
    context: Context,
    latitude: Float,
    longitude: Float,
) {
    val mapIntent: Intent = Uri.parse(
        "geo: $latitude, $longitude"
    ).let { location ->
        Intent(Intent.ACTION_VIEW, location)
    }

    Text(
        modifier = Modifier
            .padding(top = EXTRA_SMALL_PADDING)
            .clickable { context.startActivity(mapIntent) },
        text = "Country location: $latitude, $longitude",
        fontFamily = FontFamily.Monospace,
        fontSize = 18.sp,
        color = MaterialTheme.colors.contentTextColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TextModel(text: String) {
    Text(
        modifier = Modifier.padding(top = EXTRA_SMALL_PADDING),
        text = text,
        fontFamily = FontFamily.Monospace,
        fontSize = 18.sp,
        color = MaterialTheme.colors.contentTextColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun handleBankName(bank: Bank): String {
    return bank.name.ifBlank { "Bank Name" }
}

@Composable
fun handleImage(scheme: String?): Int {
    return when (scheme) {
        "visa" -> {
            R.drawable.ic_visa
        }
        "mastercard" -> {
            R.drawable.ic_mastercard
        }
        "discover" -> {
            R.drawable.ic_discover_card
        }
        else -> {
            R.drawable.ic_unknown
        }
    }
}
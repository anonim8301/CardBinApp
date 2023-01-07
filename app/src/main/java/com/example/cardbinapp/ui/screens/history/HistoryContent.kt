package com.example.cardbinapp.ui.screens.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardbinapp.ui.screens.result.handleImage
import com.example.cardbinapp.ui.theme.LARGE_PADDING
import com.example.cardbinapp.ui.theme.SMALL_PADDING
import com.example.cardbinapp.ui.theme.appBgColor
import com.example.cardbinapp.R
import com.example.cardbinapp.components.CreditCard
import com.example.cardbinapp.domain.model.CardModel
import com.example.cardbinapp.domain.utils.Resource

@ExperimentalMaterialApi
@Composable
fun HistoryContent(
    allCards: Resource<List<CardModel>>,
    onCardClicked: (cardId: Int) -> Unit,
) {

    if (allCards is Resource.Success) {
        HandleListContent(cards = allCards.data, onCardClicked = onCardClicked)
    }
}

@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    cards: List<CardModel>,
    onCardClicked: (cardId: Int) -> Unit,
) {
    if (cards.isEmpty()) EmptyContent() else DisplayCards(cards = cards,
        onCardClicked = onCardClicked)
}

@ExperimentalMaterialApi
@Composable
fun DisplayCards(
    cards: List<CardModel>,
    onCardClicked: (cardId: Int) -> Unit,
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.appBgColor)
    ) {
        items(
            items = cards, key = { card ->
                card.id
            }
        ) { card ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = SMALL_PADDING,
                        start = LARGE_PADDING,
                        end = LARGE_PADDING,
                        bottom = SMALL_PADDING
                    ),
                onClick = { onCardClicked(card.id) }
            ) {
                CreditCard(
                    bankName = card.bank.name.ifBlank { "Bank Name" },
                    imageId = handleImage(scheme = card.scheme),
                    cardBin = card.number.cardBin ?: "",
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}

@Composable
fun EmptyContent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.appBgColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.ic_empty_list),
            contentDescription = ""
        )
        Text(
            text = "Empty!",
            fontFamily = FontFamily.Monospace,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

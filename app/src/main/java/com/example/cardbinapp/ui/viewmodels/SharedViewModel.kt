package com.example.cardbinapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardbinapp.domain.model.Bank
import com.example.cardbinapp.domain.model.CardModel
import com.example.cardbinapp.domain.model.Number
import com.example.cardbinapp.domain.repository.DataStoreOperations
import com.example.cardbinapp.domain.repository.Repository
import com.example.cardbinapp.domain.utils.Resource
import com.example.cardbinapp.utils.Constants.SEARCH_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStore: DataStoreOperations,
) : ViewModel() {

    private val _allCards =
        MutableStateFlow<Resource<List<CardModel>>>(Resource.Idle)
    val allCards: StateFlow<Resource<List<CardModel>>> = _allCards

    private var _currentCard: MutableStateFlow<CardModel?> = MutableStateFlow(null)
    val currentCard: StateFlow<CardModel?> = _currentCard

    private val _currentCardBin: MutableStateFlow<String> = MutableStateFlow("")

    private var _currentScreen = mutableStateOf(SEARCH_SCREEN)
    val currentScreen = _currentScreen

    private var _homeItemsVisibility = mutableStateOf(true)
    val homeItemsVisibility: State<Boolean> = _homeItemsVisibility

    private var _homeButtonEnabled = mutableStateOf(true)
    val homeButtonEnabled: State<Boolean> = _homeButtonEnabled

    private var _toHistoryScreen = mutableStateOf(false)
    val toHistoryScreen: State<Boolean> = _toHistoryScreen

    private var _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted

    init {
        readOnBoardingState()
    }

    private fun readOnBoardingState() = viewModelScope.launch(Dispatchers.IO) {
        _onBoardingCompleted.value = dataStore.readOnBoardingState().stateIn(viewModelScope).value
    }

    fun saveOnBoardingState(completed: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        dataStore.saveOnBoardingState(completed)
    }

    fun deleteAllCards() = viewModelScope.launch {
        repository.deleteAllCards()
    }

    fun changeToHistoryState(state: Boolean) {
        _toHistoryScreen.value = state
    }

    fun searchDbChangeCurrentCard(cardId: Int) {
        viewModelScope.launch {
            repository.getCardFromDb(cardId).collect() {
                _currentCard.value = it
            }
        }
    }

    fun searchCardBin(cardBin: String) = viewModelScope.launch {
        _currentCardBin.value = cardBin
        repository.searchCardInfoRemote(cardBin).let {
            if (it.isSuccessful) {
                val response = addCardBinToCardModel(it.body(), _currentCardBin.value)
                _currentCard.value = response
                addCardToDb(card = response)
                Log.d("SharedViewModel", "$response")

            } else {
                Log.e("SharedViewModel", "Failed to load data: ${it.errorBody()}")
            }
        }
    }

    fun changeParams(
        visible: Boolean,
        buttonEnabled: Boolean,
        screen: String,
    ) {
        changeHomeItemsVisibility(visible)
        changeCurrentScreen(screen)
        changeHomeButtonEnable(buttonEnabled)
    }

    private fun changeCurrentScreen(screen: String) {
        _currentScreen.value = screen
    }

    private fun changeHomeItemsVisibility(visible: Boolean) {
        _homeItemsVisibility.value = visible
    }

    private fun changeHomeButtonEnable(buttonEnabled: Boolean) {
        _homeButtonEnabled.value = buttonEnabled
    }

    fun resetCurrentCardToNull() = viewModelScope.launch {
        delay(300)
        _currentCard.value = null
    }


    private fun addCardToDb(card: CardModel?) = viewModelScope.launch {
        if (card != null) {
            repository.addCardToDb(card)
        }
    }

    fun getAllCards() {
        _allCards.value = Resource.Loading
        try {
            viewModelScope.launch {
                repository.getAllCards().collect {
                    _allCards.value = Resource.Success(it)
                }
            }
        } catch (e: Exception) {
            _allCards.value = Resource.Error(e)
        }
    }

    private fun addCardBinToCardModel(card: CardModel?, cardBin: String): CardModel? {
        return if (card != null) {
            CardModel(
                id = card.id,
                bank = card.bank ?: Bank("", "", "", ""),
                brand = card.brand,
                country = card.country,
                number = Number(
                    length = card.number.length ?: 0,
                    luhn = card.number.luhn ?: false,
                    cardBin = cardBin
                ),
                prepaid = card.prepaid,
                scheme = card.scheme,
                type = card.type
            )
        } else null
    }
}

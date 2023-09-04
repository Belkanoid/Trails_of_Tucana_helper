package com.belkanoid.trailsoftucanahelper.ui.screens.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belkanoid.trailsoftucanahelper.domain.Card
import com.belkanoid.trailsoftucanahelper.domain.CardStore

class CardViewModel : ViewModel() {
    private val _currentPairCard = MutableLiveData<Pair<Card, Card>>()
    val currentPairCard: LiveData<Pair<Card, Card>> = _currentPairCard

    private val _remainingCards = MutableLiveData<List<Pair<Int, Card>>>()
    val remainingCards: LiveData<List<Pair<Int, Card>>> = _remainingCards

    private val _currentHand = MutableLiveData(0)
    val currentHand: LiveData<Int> = _currentHand

    init {
        _remainingCards.value = CardStore.getInitialList()
    }

    fun startNextHand() {
        _remainingCards.value = CardStore.getInitialList()
        _currentPairCard.value = Pair(Card.Back, Card.Back)

    }

    fun getNextCardPair() {
        if (_remainingCards.value?.sumOf { it.first } == 1){
            _currentHand.value = _currentHand.value?.plus(1)
            return
        }

        val a = CardStore.getRandomCard()
        val b = CardStore.getRandomCard()
        _currentPairCard.value = Pair(a, b)
        _remainingCards.value = CardStore.getRemainingCardPairList()
    }


}
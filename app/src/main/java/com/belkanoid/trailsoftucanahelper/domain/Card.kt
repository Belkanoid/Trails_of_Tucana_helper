package com.belkanoid.trailsoftucanahelper.domain

import com.belkanoid.trailsoftucanahelper.R


sealed class Card(
    val cardFaceResId: Int,
    val colorHEX: Long
) {
    object Sand: Card(R.drawable.sand, 0xFFe5cbad)
    object Water: Card(R.drawable.water, 0xFF1a7790)
    object Wood: Card(R.drawable.wood, 0xFF90b655)
    object Rock: Card(R.drawable.rock, 0xFF948676)
    object Joker: Card(R.drawable.joker, 0)

    object Back: Card(R.drawable.back, 0)
}

object CardStore{

    private var sandCardRemaining = Pair(8, Card.Sand)
    private var woodCardRemaining = Pair(7, Card.Wood)
    private var rockCardRemaining = Pair(6, Card.Rock)
    private var waterCardRemaining = Pair(4, Card.Water)
    private var jokerCardRemaining = Pair(2, Card.Joker)

    private var _initialList = listOf(
        sandCardRemaining,
        woodCardRemaining,
        rockCardRemaining,
        waterCardRemaining,
        jokerCardRemaining
    )

    private var initialList = _initialList.toMutableList()

    fun getInitialList() = _initialList.toMutableList().also {
        initialList = it.toMutableList()
    }

    fun getRemainingCardPairList() = initialList.toList()

    fun getRandomCard(): Card {
        val randomCardPair = initialList.random()
        if (randomCardPair.first == 0) {
            return getRandomCard()
        }
        initialList.replaceAll {
            if (it == randomCardPair) {
                Pair(it.first - 1, it.second)
            }else {
                it
            }
        }

        return randomCardPair.second
    }


}

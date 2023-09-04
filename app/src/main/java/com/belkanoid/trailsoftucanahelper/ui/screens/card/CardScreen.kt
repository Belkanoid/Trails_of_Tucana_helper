package com.belkanoid.trailsoftucanahelper.ui.screens.card

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belkanoid.trailsoftucanahelper.R
import com.belkanoid.trailsoftucanahelper.domain.Card
import com.belkanoid.trailsoftucanahelper.ui.screens.MainActivity
import com.belkanoid.trailsoftucanahelper.ui.screens.dialog.Dialog
import com.belkanoid.trailsoftucanahelper.ui.theme.TrailsOfTucanaHelperTheme

@Composable
fun CardScreen(
    modifier: Modifier = Modifier,
    gameMode: Int = 2
) {
    val viewModel: CardViewModel = viewModel()
    val currentPairCard: State<Pair<Card, Card>> =
        viewModel.currentPairCard.observeAsState(Pair(Card.Back, Card.Back))
    val remainingCards = viewModel.remainingCards.observeAsState(emptyList())
    val currentHand by viewModel.currentHand.observeAsState(0)
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = currentHand){
        if (currentHand in 1 until gameMode) {
            showDialog = true
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                alpha = 0.25f,
            ),
    ) {

        Row {
            if (currentHand == gameMode) {
                val activity = (LocalContext.current as? MainActivity)
                Dialog(title = "Выйти из приложения?") {
                    activity?.finish()
                }
            }
            if (showDialog) {
                Dialog(title = "Следующий кон?") {
                    viewModel.startNextHand()
                    showDialog = false
                }
            }

            DeckField(
                remainingCardPair = remainingCards,
                gameStatus = HandStyleInformation(
                    count = gameMode,
                    currentHandIndex = currentHand,
                    cardRemaining = remainingCards.value.sumOf { it.first }),
                onClick = {
                    if (currentHand != gameMode) {
                        viewModel.getNextCardPair()
                    }
                }
            )
            CardField(
                cards = currentPairCard
            )
        }
    }
}

@Composable
fun InformationField(modifier: Modifier = Modifier, cardInfo: List<Pair<Int, Card>>) {
    Row(
        modifier = modifier
            .background(
                color = Color(0xFFd8b294),
                shape = RoundedCornerShape(10),
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(cardInfo.size) {
            RemainingCardTypeInfo(colorHEX = cardInfo[it].second.colorHEX, cardInfo[it].first)
        }
    }
}

@Composable
fun RemainingCardTypeInfo(colorHEX: Long, remaining: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Hexagon(color = Color(colorHEX))
        Text(
            text = remaining.toString(),
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun RowScope.DeckField(
    modifier: Modifier = Modifier,
    remainingCardPair: State<List<Pair<Int, Card>>>,
    gameStatus: HandStyleInformation,
    onClick: () -> Unit,
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        InformationField(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
                .weight(1f),
            cardInfo = remainingCardPair.value
        )
        Row(
            modifier = Modifier.weight(3.5f)
        ) {
            HandInformation(
                modifier = Modifier.weight(1f),
                handStyleInformation = gameStatus,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(3f)
                )
                Button(onClick = { onClick() }) {
                    Text(text = "Следующие карты")
                }

            }
        }
    }
}


@Composable
fun RowScope.CardField(
    cards: State<Pair<Card, Card>>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
            .weight(2f),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (cards.value.first != Card.Back) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                cardFacedResId = cards.value.first.cardFaceResId
            )
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                cardFacedResId = cards.value.second.cardFaceResId
            )
        }
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    cardFacedResId: Int = 0,
    cardBackResId: Int = R.drawable.back,
    isOpen: Boolean = true
) {
    Image(
        modifier = modifier,
        painter = painterResource(
            if (isOpen && cardFacedResId != 0) {
                cardFacedResId
            } else {
                cardBackResId
            }
        ),
        contentDescription = null
    )
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun PreviewCardScreen() {
    TrailsOfTucanaHelperTheme(darkTheme = true) {
        CardScreen()
    }
}

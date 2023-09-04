package com.belkanoid.trailsoftucanahelper.ui.screens.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class HandStyleInformation(
    val count: Int,
    val currentHandIndex: Int,
    val cardRemaining: Int
) {
    fun getTitle(currentHandIndex: Int) = when(currentHandIndex) {
        0 -> "I"
        1 -> "II"
        else -> "III"
    }
}

@Composable
fun Hand(modifier: Modifier = Modifier, number: String, current: Boolean = false) {

    Box(
        modifier = modifier
            .padding(2.dp)
            .drawBehind {
                if (current) {
                    drawCircle(
                        color = Color.Red.copy(alpha = 0.4f),
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            color = Color.White,
            fontSize = 35.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun HandInformation(
    modifier: Modifier = Modifier,
    handStyleInformation: HandStyleInformation
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            text = handStyleInformation.cardRemaining.toString(),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 35.sp
        )
        repeat(handStyleInformation.count) {
            Hand(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                number = handStyleInformation.getTitle(it),
                current = it == handStyleInformation.currentHandIndex
            )
        }
    }
}

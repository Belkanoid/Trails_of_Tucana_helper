package com.belkanoid.trailsoftucanahelper.ui.screens.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.belkanoid.trailsoftucanahelper.domain.Card
import kotlin.math.min
import kotlin.math.sqrt

@Preview
@Composable
fun Hexagon(
    modifier: Modifier = Modifier,
    color: Color = Color(Card.Sand.colorHEX)
) {

    Canvas(modifier = modifier.size(40.dp)) {
        drawPath(
            path = drawCustomHexagonPath(this.size),
            color = Color.Black,
            style = Stroke(
                width = 2f,
            )
        )
        if (color == Color(0)) {
            val colors = listOf(
                Color(Card.Sand.colorHEX),
                Color(Card.Wood.colorHEX),
                Color(Card.Rock.colorHEX),
                Color(Card.Water.colorHEX),
            )
            val gradient = Brush.horizontalGradient(colors, startX = 0.0f, endX = this.size.width)
            drawPath(
                path = drawCustomHexagonPath(this.size),
                brush = gradient,
            )

        } else {
            drawPath(
                path = drawCustomHexagonPath(this.size),
                color = color,
            )
        }

    }
}

@Preview
@Composable
fun HexagonJoker(
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(40.dp)) {
        drawPath(
            path = drawCustomHexagonPath(this.size),
            color = Color.Black,
            style = Stroke(
                width = 2f,
            )
        )

        val colors = listOf(
            Color(Card.Sand.colorHEX),
            Color(Card.Wood.colorHEX),
            Color(Card.Rock.colorHEX),
            Color(Card.Water.colorHEX),
        )
        val gradient = Brush.horizontalGradient(colors, startX = 0.0f, endX = this.size.width)
        drawPath(
            path = drawCustomHexagonPath(this.size),
            brush = gradient,
        )

    }
}

fun drawCustomHexagonPath(size: Size): Path {
    return Path().apply {
        val radius = min(size.width / 2f, size.height / 2f)
        customHexagon(radius, size)
    }
}

fun Path.customHexagon(radius: Float, size: Size) {
    val triangleHeight = (sqrt(3.0) * radius / 2)
    val centerX = size.width / 2
    val centerY = size.height / 2

    moveTo(centerX, centerY + radius)
    lineTo((centerX - triangleHeight).toFloat(), centerY + radius / 2)
    lineTo((centerX - triangleHeight).toFloat(), centerY - radius / 2)
    lineTo(centerX, centerY - radius)
    lineTo((centerX + triangleHeight).toFloat(), centerY - radius / 2)
    lineTo((centerX + triangleHeight).toFloat(), centerY + radius / 2)

    close()
}
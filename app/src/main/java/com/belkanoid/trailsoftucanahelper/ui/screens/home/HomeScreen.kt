package com.belkanoid.trailsoftucanahelper.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.belkanoid.trailsoftucanahelper.R
import com.belkanoid.trailsoftucanahelper.ui.screens.MainActivity
import com.belkanoid.trailsoftucanahelper.ui.screens.components.CustomButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onGameModeClick: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                alpha = 0.25f,
            ),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CustomButton(
                text = "Режим маленькой карты",
                onClick = {onGameModeClick(2)}
            )
            CustomButton(
                text = "Режим большой карты",
                onClick = {onGameModeClick(3)}
            )

            val activity = (LocalContext.current as? MainActivity)
            CustomButton(
                text = "Выйти из приложения",
                onClick = {activity?.finish()}
            )

        }
    }
}



@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        onGameModeClick = {}
    )
}

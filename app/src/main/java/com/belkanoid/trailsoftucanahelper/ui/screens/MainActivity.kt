package com.belkanoid.trailsoftucanahelper.ui.screens

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.belkanoid.trailsoftucanahelper.ui.screens.card.CardScreen
import com.belkanoid.trailsoftucanahelper.ui.theme.TrailsOfTucanaHelperTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.isSystemBarsVisible = false
            TrailsOfTucanaHelperTheme(
                darkTheme = true
            ) {
                MainScreen()
            }
        }
    }

}

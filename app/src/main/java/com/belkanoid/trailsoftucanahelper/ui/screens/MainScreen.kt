package com.belkanoid.trailsoftucanahelper.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.belkanoid.trailsoftucanahelper.ui.navigation.MainNavGraph
import com.belkanoid.trailsoftucanahelper.ui.navigation.Screen
import com.belkanoid.trailsoftucanahelper.ui.screens.card.CardScreen
import com.belkanoid.trailsoftucanahelper.ui.screens.home.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        MainNavGraph(
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(
                    onGameModeClick = {
                        navHostController.navigate(Screen.Card.getRouteWithArgs(it))
                    }
                )
            },
            cardScreenContent = {
                CardScreen(
                    gameMode = it
                )
            }
        )
    }
}
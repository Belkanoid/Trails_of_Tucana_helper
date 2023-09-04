package com.belkanoid.trailsoftucanahelper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.belkanoid.trailsoftucanahelper.ui.navigation.Screen.Companion.KEY_GAME_MODE

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    cardScreenContent: @Composable (Int) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ){
        composable(Screen.Home.route){
            homeScreenContent()
        }
        composable(
            route = Screen.Card.route,
            arguments = listOf(
                navArgument(KEY_GAME_MODE){
                    type = NavType.IntType
                }
            )
        ) {
            val gameMode = it.arguments?.getInt(KEY_GAME_MODE) ?: 2
            cardScreenContent(gameMode)
        }
    }
}
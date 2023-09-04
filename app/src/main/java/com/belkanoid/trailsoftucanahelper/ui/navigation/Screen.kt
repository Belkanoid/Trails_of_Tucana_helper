package com.belkanoid.trailsoftucanahelper.ui.navigation

sealed class Screen(
    val route: String
) {
    object Home: Screen(HOME_SCREEN)
    object Card: Screen(MAIN_SCREEN){
        private const val ROUTE = "card"
        fun getRouteWithArgs(gameMode: Int) = "$ROUTE/$gameMode"
    }

    companion object {
        const val KEY_GAME_MODE = "gameMode"

        private const val HOME_SCREEN = "home"
        private const val MAIN_SCREEN = "card/{$KEY_GAME_MODE}"
    }
}

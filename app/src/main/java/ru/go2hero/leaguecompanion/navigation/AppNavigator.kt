package ru.go2hero.leaguecompanion.navigation

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

class AppNavigator(val router: Router) {

    //TODO Add navigation for main screens

    fun newRootScreen(screen: Screen) {
        router.newRootScreen(screen)
    }

    fun navigateTo(screen: Screen) {
        router.navigateTo(screen)
    }

    fun backTo(screen: Screen) {
        router.backTo(screen)
    }

    fun exit() {
        router.exit()
    }
}
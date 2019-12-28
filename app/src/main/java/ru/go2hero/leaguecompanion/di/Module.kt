package ru.go2hero.leaguecompanion.di

import org.kodein.di.Kodein
import ru.terrakok.cicerone.Cicerone


val appModule = Kodein.Module("app") {
    val cicerone = Cicerone.create()




}
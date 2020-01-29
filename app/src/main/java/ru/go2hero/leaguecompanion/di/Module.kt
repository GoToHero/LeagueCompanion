package ru.go2hero.leaguecompanion.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.go2hero.leaguecompanion.base.MainPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.go2hero.leaguecompanion.navigation.AppNavigator
import ru.go2hero.leaguecompanion.repository.LCRepository

const val DI_LC_REPO = "LeagueCompanionRepository"

val appModule = Kodein.Module("app") {
    val cicerone = Cicerone.create()
    bind<NavigatorHolder>(tag = "navigatorHolder") with singleton {
        return@singleton (cicerone.navigatorHolder)
    }

    bind<Router>(tag = "routerGlobal") with singleton {
        return@singleton (cicerone.router)
    }

    //TODO Add MainPresenter
    bind<MainPresenter>(tag = "mainPresenter") with singleton {
        return@singleton (MainPresenter())
    }

    bind<AppNavigator>(tag = "navigator") with singleton {
        return@singleton (AppNavigator(instance(tag = "routerGlobal")))
    }

    //Optional add RxBus

    bind<LCRepository>(tag = DI_LC_REPO) with singleton {
        LCRepository(instance(), instance(tag = "LeagueCompanionAPI"))
    }

    //Optional add ResultStore
}
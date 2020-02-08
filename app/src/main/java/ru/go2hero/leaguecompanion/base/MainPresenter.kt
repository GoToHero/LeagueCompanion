package ru.go2hero.leaguecompanion.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance
import ru.go2hero.leaguecompanion.storage.LCStorage
import ru.terrakok.cicerone.android.pure.AppNavigator

class MainPresenter: MvpBasePresenter<MainView>(), KodeinAware {
    override val kodein: Kodein by lazy { App.instance.kodein }

    private val appNavigator: AppNavigator = kodein.direct.instance(tag = "navigator")
    private val appStorage: LCStorage = kodein.direct.instance(tag = "appStoragePreferences")
    //TODO add repository
    //private val repository:
}
package ru.go2hero.leaguecompanion.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance
import ru.terrakok.cicerone.android.pure.AppNavigator

class MainPresenter: MvpBasePresenter<MainView>(), KodeinAware {
    override val kodein: Kodein by lazy { App.instance.kodein }

    private val appNavigator: AppNavigator = kodein.direct.instance(tag = "navigator")
    //optional TODO UserProfileStorage
    //TODO add repository
    //private val repository:
}
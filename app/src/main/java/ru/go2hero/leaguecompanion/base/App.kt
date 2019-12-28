package ru.go2hero.leaguecompanion.base

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule

class App: Application(), KodeinAware {

    companion object {
        lateinit var instance: App
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidCoreModule(this@App))
        //TODO Add DI to each module
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
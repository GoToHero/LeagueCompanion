package ru.go2hero.leaguecompanion.base

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import ru.go2hero.leaguecompanion.di.appModule

class App: Application(), KodeinAware {

    companion object {
        lateinit var instance: App
    }

    override val kodein = Kodein {
        import(androidCoreModule(this@App))
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
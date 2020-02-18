package ru.go2hero.leaguecompanion.ui.start

import android.os.Bundle
import android.os.PersistableBundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import ru.go2hero.leaguecompanion.R
import ru.go2hero.leaguecompanion.base.App

class StartActivity: MvpActivity<StartView, StartPresenter>(), KodeinAware, StartView {

    private val parentKodein by lazy {
        App.instance.kodein
    }

    override val kodein: Kodein
        get() = Kodein.lazy { extend(parentKodein) }

    override fun createPresenter(): StartPresenter {
        return StartPresenter(kodein)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_start)
    }

}
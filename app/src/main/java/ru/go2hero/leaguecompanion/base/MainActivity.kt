package ru.go2hero.leaguecompanion.base

import com.hannesdorfmann.mosby3.mvp.MvpActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MainActivity: MvpActivity<MainView, MainPresenter>(), KodeinAware, MainView {
    override fun createPresenter(): MainPresenter {
        return kodein.direct.instance(tag = "mainPresenter")
    }

    private val parentKodein by lazy {
        App.instance.kodein
    }

    override val kodein: Kodein
        get() = Kodein.lazy {
            extend(parentKodein)
        }
    //why we need backdoor interface?
            /*import(Kodein.Module("backDoor") {
                bind<BackDoor>(tag = "backdoorinterface") with singleton {
                    return@singleton (object : BackDoor {
                        override fun dismissAllDialogs() {
                            this@KidmostActivity.dismissAllDialogs()
                        }
                    })
                }
            })*/


}
package ru.go2hero.leaguecompanion.base

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance
import ru.go2hero.leaguecompanion.R
import ru.go2hero.leaguecompanion.di.DI_LC_REPOSITORY
import ru.go2hero.leaguecompanion.navigation.ActivityRouter
import ru.go2hero.leaguecompanion.repository.LCRepository

class MainActivity: MvpActivity<MainView, MainPresenter>(), KodeinAware, MainView {

    private val USER_TAB: Int = 2
    private val repository: LCRepository = kodein.direct.instance(tag = DI_LC_REPOSITORY)

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkExistUsers()
    }

    private fun checkExistUsers() {
        if (repository.isUserExist()) {
            initViewElements()
        } else {
            ActivityRouter.openNewUserScreen(this)
        }

    }

    private fun initViewElements() {
        main_container.offscreenPageLimit = 1
        val adapter = ViewPagerAdapter(supportFragmentManager)
        //TODO Add screens
        //adapter.addFragment()
        main_container.adapter = adapter

        //TODO query user profile image for bottom bar

        bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_profile -> {
                    //if (supportFragmentManager.findFragmentById(R.id.main_container)  )
                    showTab(USER_TAB)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun showTab(position: Int) {
        main_container.currentItem = position
    }
}
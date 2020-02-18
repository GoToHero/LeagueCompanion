package ru.go2hero.leaguecompanion.ui.start

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance
import ru.go2hero.leaguecompanion.di.DI_LC_REPOSITORY
import ru.go2hero.leaguecompanion.repository.LCRepository

class StartPresenter(override val kodein: Kodein): MvpBasePresenter<StartView>(), KodeinAware {

    private val repositoty: LCRepository = kodein.direct.instance(tag = DI_LC_REPOSITORY)

}
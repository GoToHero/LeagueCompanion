package ru.go2hero.leaguecompanion.base

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseView: MvpView {
    fun showError(message: String)
    fun showProgress()
    fun hideProgress()
}
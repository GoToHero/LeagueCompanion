package ru.go2hero.leaguecompanion.extensions

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applyMainThreadScheduler() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.applyMainThreadScheduler() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.applyComputationThreadScheduler() =
    subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation())
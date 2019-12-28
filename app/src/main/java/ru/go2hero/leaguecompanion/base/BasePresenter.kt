package ru.go2hero.leaguecompanion.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.squareup.moshi.Moshi
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import ru.go2hero.leaguecompanion.extensions.applyMainThreadScheduler

class BasePresenter<V : BaseView> : MvpBasePresenter<V>() {
    val compositeDisposable = CompositeDisposable()

    override fun destroy() {
        super.destroy()
        compositeDisposable.dispose()
    }

    protected open fun <DATA> execute(single: Single<DATA>): Single<DATA> =
        single.applyMainThreadScheduler()
            .doOnSubscribe {
                compositeDisposable.add(it)
                ifViewAttached { view -> view.showProgress() }
            }
            .doOnError {
                    error ->
                ifViewAttached {
                        viewState ->
                    if (error is HttpException) {
//                        val jsonAdapter = Moshi.Builder()
//                                .build()
//                                .adapter(BookErrorResponse::class.java)
//                            try {
//                                val error: BookErrorResponse = jsonAdapter.fromJson(
//                                    error.response().errorBody()?.string()
//                                )
//                                when (error.errors!![0].code) {
//                                    "err.phone_is_not_verified" -> {
//
//                                    }
//                                    "err.schedule_is_paid" -> {
//                                    }
//                                }
//
//                                error.errors!!.first().detail?.let { viewState.showError(it) }
//
//
//                        } catch (e: Exception) {
//                            viewState.showError(Constants.NETWORK_ERROR)
//                        }
//                        viewState.hideProgress()
                    }
                }
            }
            .doAfterTerminate { ifViewAttached { view -> view.hideProgress() } }
}
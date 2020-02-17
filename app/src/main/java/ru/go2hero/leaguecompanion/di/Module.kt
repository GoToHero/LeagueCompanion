package ru.go2hero.leaguecompanion.di

import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.go2hero.leaguecompanion.BuildConfig
import ru.go2hero.leaguecompanion.base.MainPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.go2hero.leaguecompanion.navigation.AppNavigator
import ru.go2hero.leaguecompanion.network.service.LCAPI
import ru.go2hero.leaguecompanion.repository.LCRepository
import ru.go2hero.leaguecompanion.repository.storage.LCStorage
import ru.go2hero.leaguecompanion.repository.storage.LCStorageImpl
import java.util.concurrent.TimeUnit

const val DI_LC_REPOSITORY = "LeagueCompanionRepository"
const val DI_MODULE_APP = "app"
const val DI_NAVIGATOR_HOLDER = "navigatorHolder"
const val DI_ROUTER = "routerGlobal"
const val DI_MAIN_PRESENTER = "mainPresenter"
const val DI_NAVIGATOR = "navigator"
const val DI_STORAGE_PREFERENCES = "appStoragePreferences"
const val DI_LC_API = "LeagueCompanionAPI"
const val DI_HTTP_CLIENT = "httpClient"
const val DI_RETROFIT = "retrofit"

private const val OKHTTP_CONNECT_TIMEOUT_MS = 20_000L
private const val OKHTTP_READ_TIMEOUT_MS = 20_000L
private const val LC_BASE_URL = "https://ru.api.riotgames.com/lol/platform/v3/"
private const val TOKEN_NAME = "X-Riot-Token"
private const val TOKEN_API_KEY = "RGAPI-15fafc1e-b52d-4585-9247-a89cd431836c"


val appModule = Kodein.Module(DI_MODULE_APP) {
    val cicerone = Cicerone.create()
    bind<NavigatorHolder>(tag = DI_NAVIGATOR_HOLDER) with singleton {
        return@singleton (cicerone.navigatorHolder)
    }

    bind<Router>(tag = DI_ROUTER) with singleton { return@singleton (cicerone.router) }

    bind<MainPresenter>(tag = DI_MAIN_PRESENTER) with singleton { return@singleton (MainPresenter()) }

    bind<AppNavigator>(tag = DI_NAVIGATOR) with singleton {
        return@singleton (AppNavigator(instance(tag = DI_ROUTER)))
    }

    //Optional add RxBus

    bind<LCStorage>(tag = DI_STORAGE_PREFERENCES) with singleton { LCStorageImpl(instance()) }

    bind<LCRepository>(tag = DI_LC_REPOSITORY) with singleton {
        LCRepository(instance(), instance(tag = DI_LC_API))
    }

    bind<OkHttpClient>(tag = DI_HTTP_CLIENT) with singleton {
        OkHttpClient.Builder()
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                ))
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader(TOKEN_NAME, TOKEN_API_KEY)
                    .build()
                it.proceed(newRequest)
            }
            .build()
    }

    bind<Retrofit>(tag = DI_RETROFIT) with singleton {
        Retrofit.Builder()
            .client(instance(tag = DI_HTTP_CLIENT))
            .baseUrl(LC_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    bind<LCAPI>(tag = DI_LC_API) with singleton {
        return@singleton (instance<Retrofit>(tag = DI_RETROFIT).create(LCAPI::class.java))
    }

    //Optional add ResultStore
}
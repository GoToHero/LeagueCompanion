package ru.go2hero.leaguecompanion.repository

import android.content.Context
import ru.go2hero.leaguecompanion.network.service.LCAPI
import ru.go2hero.leaguecompanion.repository.storage.LCStorage
import ru.go2hero.leaguecompanion.repository.storage.LCStorageImpl

class LCRepository(context: Context, private val remoteDataSource: LCAPI) {

    private val localDataSource: LCStorage = LCStorageImpl(context)

    fun isUserExist() = localDataSource.isUserExist()
}
package ru.go2hero.leaguecompanion.repository.storage

import ru.go2hero.leaguecompanion.data.SummonerProfile

interface LCStorage {
    fun getUserSummonerProfile(): SummonerProfile
    fun isUserExist(): Boolean
}
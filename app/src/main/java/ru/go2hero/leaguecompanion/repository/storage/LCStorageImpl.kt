package ru.go2hero.leaguecompanion.repository.storage

import android.content.Context
import android.content.SharedPreferences
import ru.go2hero.leaguecompanion.data.SummonerProfile

private const val PROFILE_PREFERENCES_NAME = "profile_preferences"
private const val FIELD_SUMMONER_PROFILE_ICON_ID = "user_summoner_profile_icon_id"
private const val FIELD_SUMMONER_NAME = "user_summoner_name"
private const val FIELD_SUMMONER_PUUID = "user_summoner_puuid"
private const val FIELD_SUMMONER_LEVEL = "user_summoner_level"
private const val FIELD_SUMMONER_REVISION_DATE = "user_summoner_revision_date"
private const val FIELD_SUMMONER_ID = "user_summoner_id"
private const val FIELD_SUMMONER_ACCOUNT_ID = "user_summoner_account_id"

class LCStorageImpl(val context: Context): LCStorage {

    override fun getUserSummonerProfile(): SummonerProfile {
        val preferences = getProfileSharedPreferences()

        val profileIconId = getSummonerProfileIconId(preferences)
        val name = getSummonerName(preferences)
        val puuid = getSummonerPUUID(preferences)
        val summonerLevel = getSummonerLevel(preferences)
        val revisionDate = getSummonerRevisionDate(preferences)
        val id = getSummonerId(preferences)
        val accountId = getSummonerAccountId(preferences)

        return SummonerProfile(profileIconId, name, puuid, summonerLevel, revisionDate, id, accountId)
    }

    private fun getSummonerAccountId(preferences: SharedPreferences): String? {
        return preferences.getString(FIELD_SUMMONER_ACCOUNT_ID, "")
    }

    private fun getSummonerId(preferences: SharedPreferences): String? {
        return preferences.getString(FIELD_SUMMONER_ID, "")
    }

    private fun getSummonerRevisionDate(preferences: SharedPreferences): Long {
        return preferences.getLong(FIELD_SUMMONER_REVISION_DATE, -1)
    }

    private fun getSummonerLevel(preferences: SharedPreferences): Long {
        return preferences.getLong(FIELD_SUMMONER_LEVEL, -1)
    }

    private fun getSummonerPUUID(preferences: SharedPreferences): String? {
        return preferences.getString(FIELD_SUMMONER_PUUID, "")
    }

    private fun getSummonerName(preferences: SharedPreferences): String? {
        return preferences.getString(FIELD_SUMMONER_NAME, "")
    }

    private fun getSummonerProfileIconId(preferences: SharedPreferences): Int {
        return preferences.getInt(FIELD_SUMMONER_PROFILE_ICON_ID, -1)
    }

    override fun isUserExist(): Boolean {
        val user = getUserSummonerProfile()
        if (user.name.isNullOrBlank()) return false
        return true
    }

    private fun getProfileSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(PROFILE_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private fun saveSummonerProfile(summonerProfile: SummonerProfile) {
        val preferences = getProfileSharedPreferences()
        val editor = preferences.edit()
        editor.putInt(FIELD_SUMMONER_PROFILE_ICON_ID, summonerProfile.profileIconId ?: -1)
        editor.putString(FIELD_SUMMONER_NAME, summonerProfile.name)
        editor.putString(FIELD_SUMMONER_PUUID, summonerProfile.puuid)
        editor.putLong(FIELD_SUMMONER_LEVEL, summonerProfile.summonerLevel ?: -1)
        editor.putLong(FIELD_SUMMONER_REVISION_DATE, summonerProfile.revisionDate ?: -1)
        editor.putString(FIELD_SUMMONER_ID, summonerProfile.id)
        editor.putString(FIELD_SUMMONER_ACCOUNT_ID, summonerProfile.accountId)
        editor.apply()
    }

}
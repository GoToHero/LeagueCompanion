package ru.go2hero.leaguecompanion.repository

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATA_BASE_VERSION = 1
const val DATA_BASE_NAME = "summonerDb"
const val TABLE_SUMMONERS = "user_summoner"

const val KEY_ID = "_id"
const val KEY_SUMMONER_NAME = "summoner_name"
const val KEY_SUMMONER_ID = "summoner_id"
const val KEY_ACCOUNT_ID = "account_id"
const val KEY_PUUID = "puuid"
const val KEY_SUMMONER_LVL = "summoner_lvl"
const val KEY_REVISION_DATE = "revision_date"
const val KEY_PROFILE_ICON_ID = "profile_icon_id"

@SuppressLint("NewApi")
class DBHelper(
    context: Context?
): SQLiteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
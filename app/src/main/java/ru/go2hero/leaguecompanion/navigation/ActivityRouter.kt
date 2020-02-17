package ru.go2hero.leaguecompanion.navigation

import android.app.Activity
import android.content.Intent

object ActivityRouter {

    fun openNewUserScreen(context: Activity?) {
        if (context == null) return

        val intent = Intent(context, StartActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        context.startActivity(intent)
        context.overridePendingTransition(0, 0)
    }
}
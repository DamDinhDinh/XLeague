package com.dinhdd.xleague.presenter.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.dinhdd.xleague.presenter.MainActivity
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.google.gson.Gson
import kotlin.random.Random

object AlarmManagerUtils {

    fun setMatchNotificationNotification(match: MatchPresent, context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, MatchStartingNotificationReceiver::class.java)
        intent.putExtra(MatchStartingNotificationReceiver.MATCH_BUNDLE_KEY, Gson().toJson(match))
        val pendingIntent = PendingIntent.getBroadcast(context, Random.nextInt(), intent, PendingIntent.FLAG_IMMUTABLE)
        val mainActivityIntent = Intent(context, MainActivity::class.java)
        val basicPendingIntent =
            PendingIntent.getActivity(context, Random.nextInt(), mainActivityIntent, PendingIntent.FLAG_IMMUTABLE)
        val clockInfo = AlarmManager.AlarmClockInfo(match.date.getDateOfMatchMillisecond(), basicPendingIntent)
        alarmManager.setAlarmClock(clockInfo, pendingIntent)
    }
}
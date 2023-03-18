package com.dinhdd.xleague.presenter.util

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.google.gson.Gson
import kotlin.random.Random


class MatchStartingNotificationReceiver : BroadcastReceiver() {

    companion object {
        const val MATCH_BUNDLE_KEY = "MATCH_BUNDLE_KEY"
    }

    @SuppressLint("InvalidWakeLockTag")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "")
        wl.acquire(1*60*1000L /*1 minutes*/)

        val matchJson = intent.getStringExtra(MATCH_BUNDLE_KEY)
        val match = Gson().fromJson(matchJson, MatchPresent::class.java)
        val notification = NotificationUtils.createMatchStartingNotification(match = match, context = context)
        NotificationUtils.notify(Random.nextInt(), notification, context)

        wl.release();
    }
}
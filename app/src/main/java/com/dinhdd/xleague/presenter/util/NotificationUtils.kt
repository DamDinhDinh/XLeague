package com.dinhdd.xleague.presenter.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.google.gson.Gson
import java.util.concurrent.TimeUnit

object NotificationUtils {

    private const val MATCH_STARTING_CHANNEL_ID = "MATCH_STARTING_CHANNEL_ID"
    private const val MATCH_STARTING_WORK_TAG = "MATCH_STARTING_WORK_TAG"

    fun createMatchStartingNotification(match: MatchPresent, context: Context): Notification {
        return Notification.Builder(context, MATCH_STARTING_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(match.description)
            .setContentText("${match.description} ${match.formattedDate} ${match.formattedTime}")
            .setAutoCancel(true)
            .build()
    }

    fun notify(notificationId: Int, notification: Notification, context: Context) {
        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }

    fun createMatchStartingNotificationChannel(context: Context) {
        val name = context.getString(R.string.notification_match_starting_channel_name)
        val descriptionText = context.getString(R.string.notification_match_starting_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(MATCH_STARTING_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun scheduleMatchStartingNotification(match: MatchPresent, context: Context) {
        val initialDelay = match.date.getTimeLeftOfMatchFromNow()
        val data = Data.Builder()
            .apply { putString(OneTimeScheduleMatchStaringWorker.MATCH_ARGS_KEY, Gson().toJson(match)) }
            .build()
        val work =
            OneTimeWorkRequestBuilder<OneTimeScheduleMatchStaringWorker>()
                .setInitialDelay(if (initialDelay > 0) initialDelay else 0, TimeUnit.MILLISECONDS)
                .addTag(MATCH_STARTING_WORK_TAG)
                .setInputData(data)
                .build()

        WorkManager.getInstance(context).enqueue(work)
    }
}
package com.dinhdd.xleague.presenter.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.MainActivity
import com.dinhdd.xleague.presenter.model.MatchPresent

object NotificationUtils {

    private const val TAG = "NotificationUtils"

    private const val MATCH_STARTING_CHANNEL_ID = "MATCH_STARTING_CHANNEL_ID"
    private const val MATCH_STARTING_WORK_TAG = "MATCH_STARTING_WORK_TAG"

    fun createMatchStartingNotification(
        match: MatchPresent,
        context: Context,
    ): Notification {
        val tapResultIntent = Intent(context, MainActivity::class.java)
        tapResultIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        return Notification.Builder(context, MATCH_STARTING_CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_launcher)
            setContentTitle(match.description)
            setContentText("${match.description} ${match.formattedDate} ${match.formattedTime}")
            setAutoCancel(true)
            setContentIntent(pendingIntent)
        }.build()
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
        AlarmManagerUtils.setMatchNotificationNotification(match, context)
        Toast.makeText(context, context.getString(R.string.notification_notify_set), Toast.LENGTH_SHORT).show()
    }
}
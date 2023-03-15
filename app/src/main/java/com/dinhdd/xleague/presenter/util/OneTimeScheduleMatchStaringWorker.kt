package com.dinhdd.xleague.presenter.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.google.gson.Gson
import kotlin.random.Random

class OneTimeScheduleMatchStaringWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    companion object {
        const val MATCH_ARGS_KEY = "MATCH_ARGS_KEY"
    }

    override fun doWork(): Result {
        val matchJson = inputData.getString(MATCH_ARGS_KEY)
        val match = Gson().fromJson(matchJson, MatchPresent::class.java)

        val notification = NotificationUtils.createMatchStartingNotification(match = match, context = context)
        NotificationUtils.notify(Random.nextInt(), notification, context)

        return Result.success()
    }
}
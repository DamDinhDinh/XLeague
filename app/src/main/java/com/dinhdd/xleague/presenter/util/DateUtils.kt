package com.dinhdd.xleague.presenter.util

import java.text.SimpleDateFormat
import java.util.*

const val MATCH_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun String.stringToDate(pattern: String?): Date? {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
    return simpleDateFormat.parse(this)
}

fun Date.dateToString(pattern: String?): String? {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
    return simpleDateFormat.format(this)
}

fun String.getDateOfMatch(): String {
    val date = this.stringToDate(MATCH_DATE_PATTERN)
    return date?.dateToString("dd MMM yyyy") ?: ""
}

fun String.getTimeOfMatch(): String {
    val date = this.stringToDate(MATCH_DATE_PATTERN)
    return date?.dateToString("HH:mm") ?: ""
}

fun Date.timeLeftFromNow(): Long {
    val currentTime = System.currentTimeMillis()
    val futureTime = this.time

    return futureTime - currentTime
}

fun String.getTimeLeftOfMatchFromNow(): Long {
    val date = this.stringToDate(MATCH_DATE_PATTERN)
    return date?.timeLeftFromNow() ?: throw IllegalArgumentException()
}
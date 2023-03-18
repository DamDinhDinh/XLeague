package com.dinhdd.xleague.presenter.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val MATCH_DATE_PATTERN_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

const val MATCH_DATE_PATTERN_SHOW = "dd MMM yyyy"
const val MATCH_TIME_PATTERN_SHOW = "HH:mm"

fun String.toLocalDate(fromPattern: String?): LocalDateTime? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(fromPattern)
        LocalDateTime.parse(this, formatter)
    } catch (e: Exception) {
        null
    }
}

fun LocalDateTime.toString(toPattern: String?): String? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(toPattern)
        this.format(formatter)
    } catch (e: Exception) {
        null
    }
}

fun String.getFormattedDateOfMatch(): String {
    return this
        .toLocalDate(fromPattern = MATCH_DATE_PATTERN_SERVER)
        ?.toString(toPattern = MATCH_DATE_PATTERN_SHOW)
        ?: ""
}

fun String.getFormattedTimeOfMatch(): String {
    return this
        .toLocalDate(fromPattern = MATCH_DATE_PATTERN_SERVER)
        ?.toString(toPattern = MATCH_TIME_PATTERN_SHOW)
        ?: ""
}

fun LocalDateTime.timeLeftFromNow(currentTime: Long = System.currentTimeMillis()): Long {
    val futureTime = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    return futureTime - currentTime
}

fun String.getTimeLeftOfMatchFromNow(currentTime: Long = System.currentTimeMillis()): Long {
    val date = this.toLocalDate(MATCH_DATE_PATTERN_SERVER)
    return date?.timeLeftFromNow(currentTime) ?: throw IllegalArgumentException()
}

fun String.getDateOfMatchMillisecond(): Long {
    return this
        .toLocalDate(MATCH_DATE_PATTERN_SERVER)
        ?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli() ?: 0
}
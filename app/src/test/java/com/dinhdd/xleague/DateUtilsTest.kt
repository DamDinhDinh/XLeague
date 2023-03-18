package com.dinhdd.xleague

import com.dinhdd.xleague.presenter.util.*
import junit.framework.TestCase.*
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId

class DateUtilsTest {

    @Test
    fun stringToLocalDateTestThenSuccess() {
        val stringDate = "2022-04-23T18:00:00.000Z"
        val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        val result = stringDate.toLocalDate(pattern)

        assertNotNull(result)
        assertEquals(23, result?.dayOfMonth)
        assertEquals(4, result?.monthValue)
        assertEquals(2022, result?.year)
        assertEquals(18, result?.hour)
        assertEquals(0, result?.minute)
        assertEquals(0, result?.second)
    }

    @Test
    fun stringToLocalDateTestThenFailedReturnNull() {
        val stringDate = "2022-04-23T18:00:00.000Z"
        val pattern = "dd-MM-yyy"

        val result = stringDate.toLocalDate(pattern)
        assertNull(result)
    }

    @Test
    fun localDateToStringThenSuccess() {
        val givenYear = 2023
        val givenMonth = 3
        val givenDay = 18
        val givenHour = 18
        val givenMinute = 0
        val givenSecond = 0
        val givenLocalDate = LocalDateTime.of(givenYear, givenMonth, givenDay, givenHour, givenMinute, givenSecond)

        val pattern = "yyyy-MM-dd HH:mm"
        val result = givenLocalDate.toString(pattern)

        assertNotNull(result)
        assertEquals("2023-03-18 18:00", result)
    }

    @Test
    fun localDateToStringFailedReturnNull() {
        val givenYear = 2023
        val givenMonth = 3
        val givenDay = 18
        val givenHour = 18
        val givenMinute = 0
        val givenSecond = 0
        val givenLocalDate = LocalDateTime.of(givenYear, givenMonth, givenDay, givenHour, givenMinute, givenSecond)

        val pattern = "abcedef"
        val result = givenLocalDate.toString(pattern)

        assertNull(result)
    }

    @Test
    fun getFormattedDateOfMatchThenSuccess() {
        val stringDate = "2022-04-23T18:00:00.000Z"

        val result = stringDate.getFormattedDateOfMatch()

        assertNotNull(result)
        assertEquals("23 Apr 2022", result)
    }

    @Test
    fun getFormattedDateOfMatchThenFailedReturnEmpty() {
        val stringDate = "23-04-2023"

        val result = stringDate.getFormattedDateOfMatch()

        assertNotNull(result)
        assertEquals("", result)
    }

    @Test
    fun getFormattedTimeOfMatchThenSuccess() {
        val stringDate = "2022-04-23T18:00:00.000Z"

        val result = stringDate.getFormattedTimeOfMatch()

        assertNotNull(result)
        assertEquals("18:00", result)
    }

    @Test
    fun getFormattedTimeOfMatchThenFailedReturnEmpty() {
        val stringDate = "23-04-2023"

        val result = stringDate.getFormattedTimeOfMatch()

        assertNotNull(result)
        assertEquals("", result)
    }

    @Test
    fun getTimeLeftFromNowThenSuccess() {
        val localDateTime = LocalDateTime.now()
        val oneDayLater = localDateTime.plusDays(1)

        val result =
            oneDayLater.timeLeftFromNow(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
        assertNotNull(result)
        assertEquals(86400000, result)
    }

    @Test
    fun getTimeLeftOfMatchFromNowThenSuccess() {
        val stringDate = "2023-03-19T18:00:00.000Z"
        val givenYear = 2023
        val givenMonth = 3
        val givenDay = 18
        val givenHour = 18
        val givenMinute = 0
        val givenSecond = 0
        val givenLocalDate = LocalDateTime.of(givenYear, givenMonth, givenDay, givenHour, givenMinute, givenSecond)


        val result =
            stringDate.getTimeLeftOfMatchFromNow(
                givenLocalDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            )
        assertNotNull(result)
        assertEquals(86400000, result)
    }

    @Test
    fun getTimeLeftOfMatchFromNowThenFailedException() {
        val stringDate = "2022-1-29T18:00:00.000Z"
        val givenYear = 2023
        val givenMonth = 3
        val givenDay = 18
        val givenHour = 18
        val givenMinute = 0
        val givenSecond = 0
        val givenLocalDate = LocalDateTime.of(givenYear, givenMonth, givenDay, givenHour, givenMinute, givenSecond)

        try {
            stringDate.getTimeLeftOfMatchFromNow(
                givenLocalDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            )
        } catch (e: Exception) {
            assertTrue(e is IllegalArgumentException)
        }
    }
}
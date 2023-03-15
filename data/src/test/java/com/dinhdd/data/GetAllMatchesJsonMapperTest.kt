package com.dinhdd.data

import com.dinhdd.data.remote.model.get_all_matches.PreviouJson
import com.dinhdd.data.remote.model.get_all_matches.UpcomingJson
import com.dinhdd.data.remote.model.get_all_matches.toDomain
import com.dinhdd.domain.model.Match
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllMatchesJsonMapperTest {

    lateinit var faker: Faker

    @Before
    fun setUp() {
        faker = Faker()
    }

    @Test
    fun checkMappingPreviouJsonMatchToDomain() {
        val givenPreviouJson: PreviouJson = faker.randomProvider.randomClassInstance()

        val resultMatch = givenPreviouJson.toDomain()

        assertEquals(givenPreviouJson.date, resultMatch.date)
        assertEquals(givenPreviouJson.description, resultMatch.description)
        assertEquals(givenPreviouJson.home, resultMatch.homeTeam)
        assertEquals(givenPreviouJson.away, resultMatch.awayTeam)
        assertEquals(givenPreviouJson.winner, resultMatch.winner)
        assertEquals(givenPreviouJson.highlights, resultMatch.highlightsUrl)
        assertEquals(Match.MatchType.Previous, resultMatch.matchType)
    }

    @Test
    fun checkMappingNullPreviouJsonMatchToDomain() {
        val givenPreviouJson: PreviouJson? = null

        val resultMatch = givenPreviouJson.toDomain()

        assertEquals("", resultMatch.date)
        assertEquals("", resultMatch.description)
        assertEquals("", resultMatch.homeTeam)
        assertEquals("", resultMatch.awayTeam)
        assertEquals("", resultMatch.winner)
        assertEquals("", resultMatch.highlightsUrl)
        assertEquals(Match.MatchType.Unknown, resultMatch.matchType)
    }

    @Test
    fun checkMappingUpcomingJsonMatchToDomain() {
        val givenUpcomingJson: UpcomingJson = faker.randomProvider.randomClassInstance()

        val resultMatch = givenUpcomingJson.toDomain()

        assertEquals(givenUpcomingJson.date, resultMatch.date)
        assertEquals(givenUpcomingJson.description, resultMatch.description)
        assertEquals(givenUpcomingJson.home, resultMatch.homeTeam)
        assertEquals(givenUpcomingJson.away, resultMatch.awayTeam)
        assertEquals("", resultMatch.winner)
        assertEquals("", resultMatch.highlightsUrl)
        assertEquals(Match.MatchType.UpComing, resultMatch.matchType)
    }

    @Test
    fun checkMappingNullUpcomingJsonMatchToDomain() {
        val givenUpcomingJson: UpcomingJson? = null

        val resultMatch = givenUpcomingJson.toDomain()

        assertEquals("", resultMatch.date)
        assertEquals("", resultMatch.description)
        assertEquals("", resultMatch.homeTeam)
        assertEquals("", resultMatch.awayTeam)
        assertEquals("", resultMatch.winner)
        assertEquals("", resultMatch.highlightsUrl)
        assertEquals(Match.MatchType.Unknown, resultMatch.matchType)
    }
}
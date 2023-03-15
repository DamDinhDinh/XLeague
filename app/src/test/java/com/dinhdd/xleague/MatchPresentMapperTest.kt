package com.dinhdd.xleague

import com.dinhdd.domain.model.Match
import com.dinhdd.xleague.presenter.mapper.toPresent
import com.dinhdd.xleague.presenter.model.MatchPresent
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MatchPresentMapperTest {

    lateinit var faker: Faker

    @Before
    fun setUp() {
        faker = Faker()
    }

    @Test
    fun checkMappingMatchDomainToPresent() {
        val givenMatch: Match = faker.randomProvider.randomClassInstance()

        val resultMatch = givenMatch.toPresent()

        assertEquals(givenMatch.date, resultMatch.date)
        assertEquals(givenMatch.description, resultMatch.description)
        assertEquals(givenMatch.homeTeam, resultMatch.homeTeam)
        assertEquals(givenMatch.awayTeam, resultMatch.awayTeam)
        assertEquals(givenMatch.winner, resultMatch.winner)
        assertEquals(givenMatch.highlightsUrl, resultMatch.highlightsUrl)
    }

    @Test
    fun checkMappingMatchDomainTypeToPresentType() {
        val givenMatchTypeUnknown = Match.MatchType.Unknown
        val givenMatchTypePrevious = Match.MatchType.Previous
        val givenMatchTypeUpcoming = Match.MatchType.UpComing

        val resultMatchTypeUnknown = givenMatchTypeUnknown.toPresent()
        val resultMatchTypePrevious = givenMatchTypePrevious.toPresent()
        val resultMatchTypeUpcoming = givenMatchTypeUpcoming.toPresent()

        assertEquals(MatchPresent.MatchType.Unknown, resultMatchTypeUnknown)
        assertEquals(MatchPresent.MatchType.Previous, resultMatchTypePrevious)
        assertEquals(MatchPresent.MatchType.UpComing, resultMatchTypeUpcoming)
    }
}
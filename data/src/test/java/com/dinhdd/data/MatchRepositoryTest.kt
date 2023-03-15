package com.dinhdd.data

import com.dinhdd.data.remote.model.get_all_matches.GetAllMatchesResponse
import com.dinhdd.data.remote.model.get_all_matches.MatchesJson
import com.dinhdd.data.remote.model.get_all_matches.PreviouJson
import com.dinhdd.data.remote.model.get_all_matches.UpcomingJson
import com.dinhdd.data.remote.model.get_all_matches_of_team.GetAllMatchesOfTeamResponse
import com.dinhdd.data.remote.repo.MatchRepository
import com.dinhdd.data.remote.service.MatchServices
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MatchRepositoryTest {
    @Mock
    lateinit var matchServices: MatchServices

    lateinit var faker: Faker

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        faker = Faker()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllMatchesThenSuccessTest() = runTest {
        val givenPreviousMatches = mutableListOf<PreviouJson>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }
        val givenUpcomingMatches = mutableListOf<UpcomingJson>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }
        val matchJson =
            MatchesJson(previous = givenPreviousMatches, upcomingJson = givenUpcomingMatches)
        val givenResponse = GetAllMatchesResponse(matchesJson = matchJson)

        given(matchServices.getAllMatches()).willReturn(givenResponse)

        MatchRepository(matchServices).getAllMatches().collect { resultMatches ->
            assertTrue(resultMatches.size == givenPreviousMatches.size + givenUpcomingMatches.size)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllMatchesOfTeamThenSuccessTest() = runTest {
        val givenPreviousMatches =
            mutableListOf<com.dinhdd.data.remote.model.get_all_matches_of_team.PreviouJson>().apply {
                repeat(5) {
                    add(faker.randomProvider.randomClassInstance())
                }
            }
        val givenUpcomingMatches =
            mutableListOf<com.dinhdd.data.remote.model.get_all_matches_of_team.UpcomingJson>().apply {
                repeat(5) {
                    add(faker.randomProvider.randomClassInstance())
                }
            }
        val matchJson = com.dinhdd.data.remote.model.get_all_matches_of_team.MatchesJson(
            previous = givenPreviousMatches,
            upcomingJson = givenUpcomingMatches
        )
        val givenResponse = GetAllMatchesOfTeamResponse(matchesJson = matchJson)

        given(matchServices.getAllMatchesOfTeam("1")).willReturn(givenResponse)

        MatchRepository(matchServices).getMatchesOfTeam("1").collect { resultMatches ->
            assertTrue(resultMatches.size == givenPreviousMatches.size + givenUpcomingMatches.size)
        }
    }
}
package com.dinhdd.domain

import com.dinhdd.domain.data_source.MatchDataSource
import com.dinhdd.domain.model.Match
import com.dinhdd.domain.usecase.GetAllMatchesUseCase
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllMatchesUseCaseTest {
    @Mock
    lateinit var matchDataSource: MatchDataSource

    lateinit var faker: Faker

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        faker = Faker()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllMatchesOfTeamThenSuccess() = runTest {
        val givenMatches = mutableListOf<Match>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }

        given(matchDataSource.getAllMatches()).willReturn(flow {
            emit(givenMatches)
        })

        val useCase = GetAllMatchesUseCase(matchDataSource)
        useCase().collect { resultMatches ->
            assertTrue(
                givenMatches.firstOrNull()?.date == resultMatches.firstOrNull()?.date
                        && givenMatches.firstOrNull()?.description == resultMatches.firstOrNull()?.description
                        && givenMatches.firstOrNull()?.homeTeam == resultMatches.firstOrNull()?.homeTeam
                        && givenMatches.firstOrNull()?.awayTeam == resultMatches.firstOrNull()?.awayTeam
                        && givenMatches.firstOrNull()?.winner == resultMatches.firstOrNull()?.winner
                        && givenMatches.firstOrNull()?.highlightsUrl == resultMatches.firstOrNull()?.highlightsUrl
                        && givenMatches.firstOrNull()?.matchType == resultMatches.firstOrNull()?.matchType
            )
        }
    }
}
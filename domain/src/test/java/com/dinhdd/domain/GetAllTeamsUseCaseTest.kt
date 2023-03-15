package com.dinhdd.domain

import com.dinhdd.domain.data_source.TeamDataSource
import com.dinhdd.domain.model.Team
import com.dinhdd.domain.usecase.GetAllTeamsUseCase
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

class GetAllTeamsUseCaseTest {
    @Mock
    lateinit var teamDataSource: TeamDataSource

    lateinit var faker: Faker

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        faker = Faker()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllMatchesOfTeamThenSuccess() = runTest {
        val givenTeams = mutableListOf<Team>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }

        given(teamDataSource.getAllTeams()).willReturn(flow {
            emit(givenTeams)
        })

        val useCase = GetAllTeamsUseCase(teamDataSource)
        useCase().collect { resultTeams ->
            assertTrue(
                givenTeams.firstOrNull()?.id == resultTeams.firstOrNull()?.id
                        && givenTeams.firstOrNull()?.name == resultTeams.firstOrNull()?.name
                        && givenTeams.firstOrNull()?.logoUrl == resultTeams.firstOrNull()?.logoUrl
            )
        }
    }
}
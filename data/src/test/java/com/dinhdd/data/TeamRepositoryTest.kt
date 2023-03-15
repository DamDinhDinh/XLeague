package com.dinhdd.data

import com.dinhdd.data.remote.model.get_all_teams.GetAllTeamsResponse
import com.dinhdd.data.remote.model.get_all_teams.TeamJson
import com.dinhdd.data.remote.repo.TeamRepository
import com.dinhdd.data.remote.service.TeamServices
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TeamRepositoryTest {
    @Mock
    lateinit var teamServices: TeamServices

    lateinit var faker: Faker

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        faker = Faker()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllTeamThenSuccessTest() = runTest {
        val givenTeams = mutableListOf<TeamJson>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }
        val givenResponse = GetAllTeamsResponse(teamJsons = givenTeams)

        given(teamServices.getAllTeams()).willReturn(givenResponse)

        TeamRepository(teamServices).getAllTeams().collect { resultTeams ->
            assertTrue(resultTeams.size == givenTeams.size)
        }
    }
}
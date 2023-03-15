package com.dinhdd.xleague

import app.cash.turbine.test
import com.dinhdd.domain.model.Team
import com.dinhdd.domain.usecase.GetAllTeamsUseCase
import com.dinhdd.xleague.presenter.model.TeamPresent
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingContract
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingViewModel
import com.dinhdd.xleague.test_rule.MainCoroutineRule
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TeamListingViewModelTest {
    @Mock
    lateinit var getAllTeamsUseCase: GetAllTeamsUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    lateinit var faker: Faker

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        faker = Faker()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchAllTeamsThenObserveViewState() = runTest {
        val givenTeams = mutableListOf<Team>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }

        given(getAllTeamsUseCase()).willReturn(flow { emit(givenTeams) })

        val viewModel = TeamListingViewModel(getAllTeamsUseCase)
        viewModel.fetchAllTeams()

        viewModel.observeViewState().test {
            assertEquals(givenTeams.size, awaitItem()!!.teams.size)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onTeamClickThenObserveNavigateEvent() = runTest {
        val givenTeam: TeamPresent = faker.randomProvider.randomClassInstance()

        val viewModel = TeamListingViewModel(getAllTeamsUseCase)

        viewModel.observeEvent().test {
            viewModel.onTeamClick(givenTeam)
            val result = awaitItem()
            assertTrue(result is TeamListingContract.Event.NavigateTeamMatchesListing)
            assertEquals(
                givenTeam.id,
                (result as TeamListingContract.Event.NavigateTeamMatchesListing).teamId
            )
        }
    }
}
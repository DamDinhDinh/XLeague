package com.dinhdd.xleague

import app.cash.turbine.test
import com.dinhdd.domain.model.Match
import com.dinhdd.domain.model.Team
import com.dinhdd.domain.usecase.GetAllMatchesUseCase
import com.dinhdd.domain.usecase.GetAllTeamsUseCase
import com.dinhdd.xleague.dispatcher.DispatcherProvider
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.model.TeamPresent
import com.dinhdd.xleague.presenter.screen.home_screen.HomeContract
import com.dinhdd.xleague.presenter.screen.home_screen.HomeViewModel
import com.dinhdd.xleague.test_base.MainCoroutineRule
import com.dinhdd.xleague.test_base.TestDispatcherProvider
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

class HomeViewModelTest {
    @Mock
    lateinit var getAllTeamsUseCase: GetAllTeamsUseCase

    @Mock
    lateinit var getAllMatchesUseCase: GetAllMatchesUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    lateinit var faker: Faker

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        faker = Faker()
        dispatcherProvider = TestDispatcherProvider()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchDataThenObserveViewState() = runTest {
        val givenTeams = mutableListOf<Team>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }
        val givenMatches = mutableListOf<Match>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }

        given(getAllTeamsUseCase()).willReturn(flow { emit(givenTeams) })
        given(getAllMatchesUseCase()).willReturn(flow { emit(givenMatches) })

        val viewModel = HomeViewModel(getAllTeamsUseCase, getAllMatchesUseCase, dispatcherProvider)
        viewModel.fetchData()

        val result = viewModel.observeViewState().value

        assertEquals(givenTeams.size, result?.teams?.size)
        assertEquals(givenMatches.size, result?.matches?.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onTeamClickThenObserveNavigateEvent() = runTest {
        val givenTeam: TeamPresent = faker.randomProvider.randomClassInstance()

        val viewModel = HomeViewModel(getAllTeamsUseCase, getAllMatchesUseCase, dispatcherProvider)

        viewModel.observeEvent().test {
            viewModel.onTeamClick(givenTeam)
            val result = awaitItem()
            assertTrue(result is HomeContract.Event.NavigateMatchOfTeam)
            assertEquals(
                givenTeam.id,
                (result as HomeContract.Event.NavigateMatchOfTeam).team.id
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onPreviousMatchClickThenObserveEventNavigateMatchHighlight() = runTest {
        val givenMatch: MatchPresent =
            faker.randomProvider.randomClassInstance<MatchPresent>().copy(matchType = MatchPresent.MatchType.Previous)

        val viewModel = HomeViewModel(getAllTeamsUseCase, getAllMatchesUseCase, dispatcherProvider)
        viewModel.observeEvent().test {
            viewModel.onMatchClick(givenMatch)
            val result = awaitItem()
            assertTrue(result is HomeContract.Event.NavigateMatchHighlight)
            assertEquals(
                givenMatch.highlightsUrl,
                (result as HomeContract.Event.NavigateMatchHighlight).match.highlightsUrl
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onUpcomingMatchClickThenObserveCreateMatchStartingNotificationEvent() = runTest {
        val givenMatch: MatchPresent =
            faker.randomProvider.randomClassInstance<MatchPresent>().copy(matchType = MatchPresent.MatchType.UpComing)

        val viewModel = HomeViewModel(getAllTeamsUseCase, getAllMatchesUseCase, dispatcherProvider)
        viewModel.observeEvent().test {
            viewModel.onMatchClick(givenMatch)
            val result = awaitItem()
            assertTrue(result is HomeContract.Event.CreateMatchStartingNotification)
            assertEquals(
                givenMatch.date,
                (result as HomeContract.Event.CreateMatchStartingNotification).match.date
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onTeamListingClickThenObserveNavigateEvent() = runTest {
        val viewModel = HomeViewModel(getAllTeamsUseCase, getAllMatchesUseCase, dispatcherProvider)

        viewModel.observeEvent().test {
            viewModel.onTeamListingClick()
            val result = awaitItem()
            assertTrue(result is HomeContract.Event.NavigateTeamListing)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onMatchListingClickThenObserveNavigateEvent() = runTest {
        val viewModel = HomeViewModel(getAllTeamsUseCase, getAllMatchesUseCase, dispatcherProvider)

        viewModel.observeEvent().test {
            viewModel.onMatchListingClick()
            val result = awaitItem()
            assertTrue(result is HomeContract.Event.NavigateMatchListing)
        }
    }
}
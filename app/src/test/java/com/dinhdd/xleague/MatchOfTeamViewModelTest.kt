package com.dinhdd.xleague

import app.cash.turbine.test
import com.dinhdd.domain.model.Match
import com.dinhdd.domain.usecase.GetAllMatchesOfTeamUseCase
import com.dinhdd.xleague.dispatcher.DispatcherProvider
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.screen.matches_of_team.MatchesOfTeamContract
import com.dinhdd.xleague.presenter.screen.matches_of_team.MatchesOfTeamViewModel
import com.dinhdd.xleague.test_base.MainCoroutineRule
import com.dinhdd.xleague.test_base.TestDispatcherProvider
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MatchOfTeamViewModelTest {
    @Mock
    lateinit var getMatchOfTeamUseCase: GetAllMatchesOfTeamUseCase

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
    fun fetchMatchOfTeamThenObserveViewState() = runTest {
        val givenPreviousMatchCount = 5
        val givenUpcomingMatchCount = 5
        val givenMatches = mutableListOf<Match>().apply {
            repeat(givenPreviousMatchCount) {
                add(faker.randomProvider.randomClassInstance<Match>().copy(matchType = Match.MatchType.Previous))
            }
            repeat(givenUpcomingMatchCount) {
                add(faker.randomProvider.randomClassInstance<Match>().copy(matchType = Match.MatchType.UpComing))
            }
        }
        given(getMatchOfTeamUseCase("1")).willReturn(flow { emit(givenMatches) })

        val viewModel = MatchesOfTeamViewModel(getMatchOfTeamUseCase, dispatcherProvider)
        viewModel.fetchMatchOfTeam("1")
        val result = viewModel.observeViewState().value

        assertNotNull(result)
        assertEquals(givenPreviousMatchCount, result?.previousMatches?.size)
        assertEquals(givenUpcomingMatchCount, result?.previousMatches?.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onPreviousMatchClickThenObserveEventNavigateMatchHighlight() = runTest {
        val givenMatch: MatchPresent =
            faker.randomProvider.randomClassInstance<MatchPresent>().copy(matchType = MatchPresent.MatchType.Previous)

        val viewModel = MatchesOfTeamViewModel(getMatchOfTeamUseCase, dispatcherProvider)
        viewModel.observeEvent().test {
            viewModel.onMatchClick(givenMatch)
            val result = awaitItem()

            assertNotNull(result)
            assertTrue(result is MatchesOfTeamContract.Event.NavigateMatchHighlight)
            assertEquals(
                givenMatch.highlightsUrl,
                (result as MatchesOfTeamContract.Event.NavigateMatchHighlight).match.highlightsUrl
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onUpcomingMatchClickThenObserveCreateMatchStartingNotificationEvent() = runTest {
        val givenMatch: MatchPresent =
            faker.randomProvider.randomClassInstance<MatchPresent>().copy(matchType = MatchPresent.MatchType.UpComing)

        val viewModel = MatchesOfTeamViewModel(getMatchOfTeamUseCase, dispatcherProvider)
        viewModel.observeEvent().test {
            viewModel.onMatchClick(givenMatch)
            val result = awaitItem()

            assertNotNull(result)
            assertTrue(result is MatchesOfTeamContract.Event.CreateMatchStartingNotification)
            assertEquals(
                givenMatch.date,
                (result as MatchesOfTeamContract.Event.CreateMatchStartingNotification).match.date
            )
        }
    }
}
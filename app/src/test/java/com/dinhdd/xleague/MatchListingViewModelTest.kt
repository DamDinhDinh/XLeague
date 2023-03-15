package com.dinhdd.xleague

import app.cash.turbine.test
import com.dinhdd.domain.model.Match
import com.dinhdd.domain.usecase.GetAllMatchesUseCase
import com.dinhdd.xleague.dispatcher.DispatcherProvider
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingContract
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingViewModel
import com.dinhdd.xleague.test_base.MainCoroutineRule
import com.dinhdd.xleague.test_base.TestDispatcherProvider
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MatchListingViewModelTest {
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
    fun fetchAllMatchesThenObserveViewState() = runTest {
        val givenMatches = mutableListOf<Match>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }

        given(getAllMatchesUseCase()).willReturn(flow { emit(givenMatches) })

        val viewModel = MatchListingViewModel(getAllMatchesUseCase, dispatcherProvider)
        viewModel.fetchAllMatches()

        val value = viewModel.observeViewState().value

        assertEquals(givenMatches.size, value?.matches?.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onPreviousMatchClickThenObserveEventNavigateMatchHighlight() = runTest {
        val givenMatch: MatchPresent =
            faker.randomProvider.randomClassInstance<MatchPresent>().copy(matchType = MatchPresent.MatchType.Previous)

        val viewModel = MatchListingViewModel(getAllMatchesUseCase, dispatcherProvider)
        viewModel.observeEvent().test {
            viewModel.onMatchClick(givenMatch)
            val result = awaitItem()
            TestCase.assertTrue(result is MatchListingContract.Event.NavigateMatchHighlight)
            assertEquals(
                givenMatch.highlightsUrl,
                (result as MatchListingContract.Event.NavigateMatchHighlight).match.highlightsUrl
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onUpcomingMatchClickThenObserveCreateMatchStartingNotificationEvent() = runTest {
        val givenMatch: MatchPresent =
            faker.randomProvider.randomClassInstance<MatchPresent>().copy(matchType = MatchPresent.MatchType.UpComing)

        val viewModel = MatchListingViewModel(getAllMatchesUseCase, dispatcherProvider)
        viewModel.observeEvent().test {
            viewModel.onMatchClick(givenMatch)
            val result = awaitItem()
            TestCase.assertTrue(result is MatchListingContract.Event.CreateMatchStartingNotification)
            assertEquals(
                givenMatch.date,
                (result as MatchListingContract.Event.CreateMatchStartingNotification).match.date
            )
        }
    }
}
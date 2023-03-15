package com.dinhdd.xleague

import app.cash.turbine.test
import com.dinhdd.domain.model.Match
import com.dinhdd.domain.usecase.GetAllMatchesUseCase
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingViewModel
import com.dinhdd.xleague.test_rule.MainCoroutineRule
import io.github.serpro69.kfaker.Faker
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
    fun fetchAllMatchesThenObserveViewState() = runTest {
        val givenMatches = mutableListOf<Match>().apply {
            repeat(5) {
                add(faker.randomProvider.randomClassInstance())
            }
        }

        given(getAllMatchesUseCase()).willReturn(flow { emit(givenMatches) })

        val viewModel = MatchListingViewModel(getAllMatchesUseCase)
        viewModel.fetchAllMatches()

        viewModel.observeViewState().test {
            assertEquals(givenMatches.size, awaitItem()!!.matches.size)
        }
    }
}
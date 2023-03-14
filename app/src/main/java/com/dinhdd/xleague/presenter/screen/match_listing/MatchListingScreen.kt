package com.dinhdd.xleague.presenter.screen.match_listing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinhdd.xleague.presenter.screen.match_listing.view.MatchList
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme

@Composable
fun MatchListingScreen(viewModel: MatchListingContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchAllMatches()
        }
    }
    XLeagueTheme {
        state?.let { state ->
            MatchList(matches = state.matches)
        }
    }
}

@Composable
fun MatchListingScreen() {
    val viewModel: MatchListingViewModel = hiltViewModel()


    MatchListingScreen(viewModel)
}
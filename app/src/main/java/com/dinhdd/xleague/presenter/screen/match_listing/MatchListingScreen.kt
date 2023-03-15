package com.dinhdd.xleague.presenter.screen.match_listing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dinhdd.xleague.presenter.screen.home_screen.view.HomeMatchList

@Composable
fun MatchListingScreen(viewModel: MatchListingContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchAllMatches()
        }
    }

    state?.let {
        HomeMatchList(
            matches = it.matches,
            onMatchClick = { match -> viewModel.onMatchClick(match) }
        )
    }
}
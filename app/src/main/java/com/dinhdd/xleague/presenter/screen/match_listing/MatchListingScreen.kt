package com.dinhdd.xleague.presenter.screen.match_listing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.dinhdd.xleague.presenter.screen.home_screen.view.HomeMatchList
import com.dinhdd.xleague.presenter.util.NotificationUtils

@Composable
fun MatchListingScreen(viewModel: MatchListingContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    val eventState by viewModel.observeEvent().collectAsState(initial = null)

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

    when (val event = eventState) {
        is MatchListingContract.Event.CreateMatchStartingNotification -> {
            NotificationUtils.scheduleMatchStartingNotification(event.match, LocalContext.current)
        }
        else -> Unit
    }
}
package com.dinhdd.xleague.presenter.screen.match_listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.presenter.screen.common.ScreenConstant
import com.dinhdd.xleague.presenter.screen.home_screen.view.PreviousUpcomingHorizontalMatchListing
import com.dinhdd.xleague.presenter.screen.home_screen.view.PreviousUpcomingVerticalMatchListing
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
        if (it.isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            BoxWithConstraints(modifier = Modifier.padding(top = 24.dp)) {
                when {
                    maxWidth > ScreenConstant.MEDIUM_SCREEN_WIDTH -> {
                        PreviousUpcomingVerticalMatchListing(
                            previousMatches = it.previousMatches,
                            upcomingMatches = it.upcomingMatches,
                            onMatchClick = { match -> viewModel.onMatchClick(match) }
                        )
                    }
                    else -> {
                        PreviousUpcomingHorizontalMatchListing(
                            previousMatches = it.previousMatches,
                            upcomingMatches = it.upcomingMatches,
                            onMatchClick = { match -> viewModel.onMatchClick(match) }
                        )
                    }
                }
            }
        }
    }

    when (val event = eventState) {
        is MatchListingContract.Event.CreateMatchStartingNotification -> {
            NotificationUtils.scheduleMatchStartingNotification(event.match, LocalContext.current)
        }
        else -> Unit
    }
}
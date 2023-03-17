package com.dinhdd.xleague.presenter.screen.matches_of_team

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.presenter.screen.common.ScreenConstant.MEDIUM_SCREEN_WIDTH
import com.dinhdd.xleague.presenter.screen.home_screen.view.PreviousUpcomingHorizontalMatchListing
import com.dinhdd.xleague.presenter.screen.home_screen.view.PreviousUpcomingVerticalMatchListing
import com.dinhdd.xleague.presenter.util.NotificationUtils

@Composable
fun MatchesOfTeamScreen(viewModel: MatchesOfTeamContract.ViewModel, teamId: String) {
    val state by viewModel.observeViewState().collectAsState()
    val eventState by viewModel.observeEvent().collectAsState(initial = null)

    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchMatchOfTeam(teamId)
        }
    }

    state?.let {
        BoxWithConstraints(modifier = Modifier.padding(top = 24.dp)) {
            when {
                maxWidth > MEDIUM_SCREEN_WIDTH -> {
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

    when (val event = eventState) {
        is MatchesOfTeamContract.Event.CreateMatchStartingNotification -> {
            NotificationUtils.scheduleMatchStartingNotification(event.match, LocalContext.current)
        }
        else -> Unit
    }
}
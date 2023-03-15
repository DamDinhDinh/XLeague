package com.dinhdd.xleague.presenter.screen.matches_of_team

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.dinhdd.xleague.presenter.screen.home_screen.view.HomeMatchList
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
        HomeMatchList(matches = it.matches, onMatchClick = { match -> viewModel.onMatchClick(match) })
    }

    when (val event = eventState) {
        is MatchesOfTeamContract.Event.CreateMatchStartingNotification -> {
            NotificationUtils.scheduleMatchStartingNotification(event.match, LocalContext.current)
        }
        else -> Unit
    }
}
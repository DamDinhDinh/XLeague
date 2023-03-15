package com.dinhdd.xleague.presenter.screen.matches_of_team

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dinhdd.xleague.presenter.screen.match_listing.view.MatchList

@Composable
fun MatchesOfTeamScreen(viewModel: MatchesOfTeamContract.ViewModel, teamId: String) {
    val state by viewModel.observeViewState().collectAsState()

    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchMatchOfTeam(teamId)
        }
    }

    state?.let {
        MatchList(matches = it.matches)
    }
}
package com.dinhdd.xleague.presenter.screen.team_listing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dinhdd.xleague.presenter.screen.team_listing.view.TeamList

@Composable
fun TeamListingScreen(viewModel: TeamListingContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()

    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchAllTeams()
        }
    }

    state?.let {
        TeamList(teams = it.teams, onTeamClick = { team -> viewModel.onTeamClick(team) })
    }
}
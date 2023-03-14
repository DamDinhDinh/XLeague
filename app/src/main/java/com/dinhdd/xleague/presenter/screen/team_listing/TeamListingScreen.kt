package com.dinhdd.xleague.presenter.screen.team_listing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinhdd.xleague.presenter.screen.team_listing.view.TeamList
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme

@Composable
fun TeamListingScreen(viewModel: TeamListingContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()

    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchAllTeams()
        }
    }

    XLeagueTheme {
        state?.let { state ->
            TeamList(teams = state.teams, onTeamClick = { team -> viewModel.onTeamClick(team) })
        }
    }
}

@Composable
fun TeamListingScreen() {
    val viewModel: TeamListingViewModel = hiltViewModel()
    TeamListingScreen(viewModel)
}

package com.dinhdd.xleague.presenter.screen.team_listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        if (it.isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            TeamList(teams = it.teams, onTeamClick = { team -> viewModel.onTeamClick(team) })
        }
    }
}
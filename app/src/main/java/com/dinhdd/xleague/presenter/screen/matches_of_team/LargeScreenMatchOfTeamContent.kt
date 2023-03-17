package com.dinhdd.xleague.presenter.screen.matches_of_team

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.screen.home_screen.view.VerticalHomeMatchList

@Composable
fun LargeScreenMatchOfTeamContent(viewModel: MatchesOfTeamContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()

    state?.let {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            VerticalHomeMatchList(
                label = stringResource(id = R.string.home_screen_previous_matches),
                matches = it.previousMatches,
                onMatchClick = { match -> viewModel.onMatchClick(match) })

            VerticalHomeMatchList(
                label = stringResource(id = R.string.home_screen_upcoming_matches),
                matches = it.upcomingMatches,
                onMatchClick = { match -> viewModel.onMatchClick(match) })
        }
    }
}
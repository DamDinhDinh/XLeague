package com.dinhdd.xleague.presenter.screen.matches_of_team

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.screen.home_screen.view.HorizontalHomeMatchList

@Composable
fun SmallScreenMatchOfTeamContent(viewModel: MatchesOfTeamContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    state?.let {
        Column {
            Spacer(modifier = Modifier.size(24.dp))
            HorizontalHomeMatchList(
                label = stringResource(id = R.string.home_screen_previous_matches),
                matches = it.previousMatches,
                onMatchClick = { match -> viewModel.onMatchClick(match) })
            Spacer(modifier = Modifier.size(8.dp))
            HorizontalHomeMatchList(
                label = stringResource(id = R.string.home_screen_upcoming_matches),
                matches = it.upcomingMatches,
                onMatchClick = { match -> viewModel.onMatchClick(match) })
        }
    }
}
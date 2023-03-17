package com.dinhdd.xleague.presenter.screen.home_screen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.screen.home_screen.HomeContract

@Composable
fun LargeScreenHomeContent(viewModel: HomeContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()

    state?.let {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.home_screen_team_list),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable {
                            viewModel.onTeamListingClick()
                        }
                )
                Spacer(modifier = Modifier.size(8.dp))
                VerticalHomeTeamList(
                    it.teams,
                    onTeamClick = { team -> viewModel.onTeamClick(team) })
            }
            Spacer(modifier = Modifier.size(24.dp))
            PreviousUpcomingVerticalMatchListing(
                previousMatches = it.previousMatches,
                upcomingMatches = it.upcomingMatches,
                onMatchClick = { match -> viewModel.onMatchClick(match) }
            )
        }
    }
}
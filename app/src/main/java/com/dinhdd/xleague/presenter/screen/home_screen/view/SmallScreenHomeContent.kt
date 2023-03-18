package com.dinhdd.xleague.presenter.screen.home_screen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun SmallScreenHomeContent(viewModel: HomeContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()

    state?.let {
        Column {
            Spacer(modifier = Modifier.size(24.dp))
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
            HorizontalHomeTeamList(
                it.teams,
                onTeamClick = { team -> viewModel.onTeamClick(team) })
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = stringResource(id = R.string.home_screen_match_list),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable {
                        viewModel.onMatchListingClick()
                    }
            )
            Spacer(modifier = Modifier.size(8.dp))
            PreviousUpcomingHorizontalMatchListing(
                previousMatches = it.previousMatches,
                upcomingMatches = it.upcomingMatches,
                onMatchClick = { match -> viewModel.onMatchClick(match) }
            )
        }
    }
}
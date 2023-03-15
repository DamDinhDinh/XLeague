package com.dinhdd.xleague.presenter.screen.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.screen.home_screen.view.HomeMatchList
import com.dinhdd.xleague.presenter.screen.home_screen.view.HomeTeamList
import com.dinhdd.xleague.presenter.util.NotificationUtils

@Composable
fun HomeScreen(viewModel: HomeContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    val eventState by viewModel.observeEvent().collectAsState(initial = null)
    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchData()
        }
    }

    state?.let {
        when {
            it.isLoading -> {

            }
            else -> {
                Surface(modifier = Modifier.padding(24.dp)) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.home_screen_team_list),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.clickable {
                                viewModel.onTeamListingClick()
                            }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        HomeTeamList(it.teams, onTeamClick = { team -> viewModel.onTeamClick(team) })
                        Spacer(modifier = Modifier.size(24.dp))
                        Text(
                            text = stringResource(id = R.string.home_screen_match_list),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.clickable {
                                viewModel.onMatchListingClick()
                            }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        HomeMatchList(
                            matches = it.matches,
                            onMatchClick = { match -> viewModel.onMatchClick(match) })
                    }
                }
            }
        }
    }

    when (val event = eventState) {
        is HomeContract.Event.CreateMatchStartingNotification -> {
            NotificationUtils.scheduleMatchStartingNotification(event.match, LocalContext.current)
        }
        else -> Unit
    }
}
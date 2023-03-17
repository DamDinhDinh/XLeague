package com.dinhdd.xleague.presenter.screen.home_screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.model.MatchPresent

@Composable
fun PreviousUpcomingHorizontalMatchListing(
    previousMatches: List<MatchPresent>,
    upcomingMatches: List<MatchPresent>,
    modifier: Modifier = Modifier,
    onMatchClick: (MatchPresent) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxHeight(),
    ) {
        HorizontalHomeMatchList(
            label = stringResource(id = R.string.home_screen_previous_matches),
            matches = previousMatches,
            onMatchClick = { match -> onMatchClick(match) })
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalHomeMatchList(
            label = stringResource(id = R.string.home_screen_upcoming_matches),
            matches = upcomingMatches,
            onMatchClick = { match -> onMatchClick(match) })
    }

}
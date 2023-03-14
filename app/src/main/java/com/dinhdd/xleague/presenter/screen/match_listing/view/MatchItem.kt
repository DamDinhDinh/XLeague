package com.dinhdd.xleague.presenter.screen.match_listing.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.model.MatchPresent

@Composable
fun MatchItem(match: MatchPresent, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MatchInfoItem(title = stringResource(id = R.string.match_row_date), content = match.date)
        Spacer(modifier = Modifier.size(4.dp))
        MatchInfoItem(title = stringResource(id = R.string.match_row_description), content = match.description)
        Spacer(modifier = Modifier.size(4.dp))
        MatchInfoItem(title = stringResource(id = R.string.match_row_home_team), content = match.homeTeam)
        Spacer(modifier = Modifier.size(4.dp))
        MatchInfoItem(title = stringResource(id = R.string.match_row_away_team), content = match.awayTeam)
        Spacer(modifier = Modifier.size(4.dp))
        if (match.winner.isNotEmpty()) {
            MatchInfoItem(title = stringResource(id = R.string.match_row_winner_team), content = match.winner)
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
@Preview(device = Devices.NEXUS_5)
fun MatchItemPreview() {
    val match = MatchPresent(
        date = "2022-04-23T18:00:00.000Z",
        description = "Team Cool Eagles vs. Team Red Dragons",
        homeTeam = "Team Cool Eagles",
        awayTeam = "Team Red Dragons",
        winner = "Team Red Dragons",
        highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
        matchType = MatchPresent.MatchType.Previous
    )

    MatchItem(match)
}
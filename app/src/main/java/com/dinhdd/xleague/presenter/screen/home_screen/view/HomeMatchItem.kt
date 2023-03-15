package com.dinhdd.xleague.presenter.screen.home_screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.model.MatchPresent

@Composable
fun HomeMatchItem(match: MatchPresent, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(
                text = match.homeTeam, style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = match.formattedDate, style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = match.formattedTime, style = MaterialTheme.typography.body1)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = match.awayTeam,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f)
            )
        }
        if (match.matchType == MatchPresent.MatchType.Previous) {
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.match_row_winner_team),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = match.winner,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(id = R.string.match_row_click_to_view_highlight),
                    style = MaterialTheme.typography.body1
                )
            }
        } else {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.match_row_click_to_set_notify),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
@Preview(device = Devices.NEXUS_5)
fun HomeMatchItemPreview() {
    val match = MatchPresent(
        date = "2022-04-23T18:00:00.000Z",
        description = "Team Cool Eagles vs. Team Red Dragons",
        homeTeam = "Team Cool Eagles",
        awayTeam = "Team Red Dragons",
        winner = "Team Red Dragons",
        highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
        matchType = MatchPresent.MatchType.Previous,
        formattedDate = "23 Mar 2022",
        formattedTime = "18:00"
    )

    HomeMatchItem(match)
}
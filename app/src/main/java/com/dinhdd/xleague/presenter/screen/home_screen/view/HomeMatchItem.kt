package com.dinhdd.xleague.presenter.screen.home_screen.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.model.MatchPresent

@Composable
fun HomeMatchItem(match: MatchPresent, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        elevation = 2.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface)
    ) {
        Column(
            modifier = Modifier
                .width(250.dp)
                .height(150.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = match.homeTeam,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = match.formattedDate,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = match.formattedTime,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = match.awayTeam,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.End
                )
            }
            if (match.matchType == MatchPresent.MatchType.Previous) {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(id = R.string.match_row_winner_team, match.winner),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.error,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(id = R.string.match_row_click_to_view_highlight),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                )
            } else {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(id = R.string.match_row_click_to_set_notify),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.error
                )
            }
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
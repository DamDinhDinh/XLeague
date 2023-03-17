package com.dinhdd.xleague.presenter.screen.home_screen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme

@Composable
fun HorizontalHomeMatchList(
    matches: List<MatchPresent>,
    modifier: Modifier = Modifier,
    label: String = "",
    onMatchClick: (MatchPresent) -> Unit = {}
) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (label.isNotBlank()) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
                content = {
                    items(matches.size) { index ->
                        HomeMatchItem(
                            match = matches[index],
                            modifier = Modifier
                                .clickable { onMatchClick(matches[index]) }
                        )
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun HorizontalHomeMatchListPreview() {
    val teamList = listOf(
        MatchPresent(
            date = "2022-04-23T18:00:00.000Z",
            description = "Team Cool Eagles vs. Team Red Dragons",
            homeTeam = "Team Cool Eagles",
            awayTeam = "Team Red Dragons",
            winner = "Team Red Dragons",
            highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
            matchType = MatchPresent.MatchType.Previous,
            formattedDate = "23 Mar 2022",
            formattedTime = "18:00"
        ),
        MatchPresent(
            date = "2022-04-23T18:00:00.000Z",
            description = "Team Cool Eagles vs. Team Red Dragons",
            homeTeam = "Team Cool Eagles",
            awayTeam = "Team Red Dragons",
            winner = "Team Red Dragons",
            highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
            matchType = MatchPresent.MatchType.Previous,
            formattedDate = "23 Mar 2022",
            formattedTime = "18:00"
        ),
        MatchPresent(
            date = "2022-04-23T18:00:00.000Z",
            description = "Team Cool Eagles vs. Team Red Dragons",
            homeTeam = "Team Cool Eagles",
            awayTeam = "Team Red Dragons",
            winner = "Team Red Dragons",
            highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
            matchType = MatchPresent.MatchType.Previous,
            formattedDate = "23 Mar 2022",
            formattedTime = "18:00"
        ),
        MatchPresent(
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
    )

    XLeagueTheme {
        HorizontalHomeMatchList(label = "Previous Matches: ", matches = teamList)
    }
}
package com.dinhdd.xleague.presenter.screen.match_listing.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme

@Composable
fun MatchList(matches: List<MatchPresent>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(matches.size) { index ->
                MatchItem(match = matches[index])
            }
        }
    )
}

@Composable
@Preview
fun MatchListPreview() {
    val teamList = listOf(
        MatchPresent(
            date = "2022-04-23T18:00:00.000Z",
            description = "Team Cool Eagles vs. Team Red Dragons",
            homeTeam = "Team Cool Eagles",
            awayTeam = "Team Red Dragons",
            winner = "Team Red Dragons",
            highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
            matchType = MatchPresent.MatchType.Previous
        ),
        MatchPresent(
            date = "2022-04-23T18:00:00.000Z",
            description = "Team Cool Eagles vs. Team Red Dragons",
            homeTeam = "Team Cool Eagles",
            awayTeam = "Team Red Dragons",
            winner = "Team Red Dragons",
            highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
            matchType = MatchPresent.MatchType.Previous
        ),
        MatchPresent(
            date = "2022-04-23T18:00:00.000Z",
            description = "Team Cool Eagles vs. Team Red Dragons",
            homeTeam = "Team Cool Eagles",
            awayTeam = "Team Red Dragons",
            winner = "Team Red Dragons",
            highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
            matchType = MatchPresent.MatchType.Previous
        ),
        MatchPresent(
            date = "2022-04-23T18:00:00.000Z",
            description = "Team Cool Eagles vs. Team Red Dragons",
            homeTeam = "Team Cool Eagles",
            awayTeam = "Team Red Dragons",
            winner = "Team Red Dragons",
            highlightsUrl = "https://tstzj.s3.amazonaws.com/highlights.mp4",
            matchType = MatchPresent.MatchType.Previous
        )
    )

    XLeagueTheme {
        MatchList(matches = teamList)
    }
}
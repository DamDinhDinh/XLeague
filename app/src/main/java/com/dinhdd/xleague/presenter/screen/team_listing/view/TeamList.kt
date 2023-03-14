package com.dinhdd.xleague.presenter.screen.team_listing.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.presenter.model.TeamPresent
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme

private val itemSize = 125.dp

@Composable
fun TeamList(teams: List<TeamPresent>, modifier: Modifier = Modifier, onTeamClick: (TeamPresent) -> Unit = {}) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(itemSize),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(teams.size) { index ->
                TeamItem(
                    team = teams[index],
                    modifier = Modifier
                        .size(itemSize)
                        .clickable { onTeamClick(teams[index]) })
            }
        }
    )
}

@Composable
@Preview
fun TeamListPreview() {
    val teamList = listOf(
        TeamPresent(id = "", name = "Team Red Dragons", logoUrl = "https://tstzj.s3.amazonaws.com/dragons.png"),
        TeamPresent(id = "", name = "Team Cool Eagles", logoUrl = "https://tstzj.s3.amazonaws.com/eagle.png"),
        TeamPresent(id = "", name = "Team Chill Elephants", logoUrl = "https://tstzj.s3.amazonaws.com/elephants.png"),
        TeamPresent(id = "", name = "Team Win Kings", logoUrl = "https://tstzj.s3.amazonaws.com/kings.png"),
        TeamPresent(id = "", name = "Team Serious Lions", logoUrl = "https://tstzj.s3.amazonaws.com/lion.png"),
        TeamPresent(id = "", name = "Team Angry Pandas", logoUrl = "https://tstzj.s3.amazonaws.com/panda.png"),
        TeamPresent(id = "", name = "Team Fiery Phoenix", logoUrl = "https://tstzj.s3.amazonaws.com/phoenix.png"),
        TeamPresent(id = "", name = "Team Hungry Sharks", logoUrl = "https://tstzj.s3.amazonaws.com/sharks.png"),
        TeamPresent(id = "", name = "Team Growling Tigers", logoUrl = "https://tstzj.s3.amazonaws.com/tigers.png"),
        TeamPresent(id = "", name = "Team Royal Knights", logoUrl = "https://tstzj.s3.amazonaws.com/knights.png"),
    )

    XLeagueTheme {
        TeamList(teams = teamList)
    }
}
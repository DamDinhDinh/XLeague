package com.dinhdd.xleague.presenter.screen.team_listing.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dinhdd.xleague.presenter.model.TeamPresent
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme

@Composable
fun TeamItem(team: TeamPresent, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = team.logoUrl),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = team.name,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary,
        )
    }
}

@Composable
@Preview(device = Devices.NEXUS_5)
fun TeamItemRowPreview() {
    val team = TeamPresent(id = "", name = "Team Red Dragons", logoUrl = "https://tstzj.s3.amazonaws.com/dragons.png")

    XLeagueTheme {
        TeamItem(team, modifier = Modifier.size(125.dp))
    }
}

@Composable
@Preview(device = Devices.NEXUS_5)
fun TeamItemRowPreview2() {
    val team = TeamPresent(id = "", name = "Team Red Dragons", logoUrl = "https://tstzj.s3.amazonaws.com/dragons.png")

    XLeagueTheme(isDarkTheme = true) {
        TeamItem(team, modifier = Modifier.size(125.dp))
    }
}
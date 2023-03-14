package com.dinhdd.xleague.presenter.screen.match_listing.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MatchInfoItem(title: String, content: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = content,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
@Preview
fun MatchInfoItemPreview() {
    MatchInfoItem(
        title = "Description:",
        content = "Team Cool Eagles vs. Team Red Dragons"
    )
}
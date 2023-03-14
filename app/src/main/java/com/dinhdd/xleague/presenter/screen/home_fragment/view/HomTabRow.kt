package com.dinhdd.xleague.presenter.screen.home_fragment.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme

@Composable
fun HomeTabRow(
    tabNames: List<String>,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {}
) {
    TabRow(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            DetailTabIndicator(
                selectedTabIndex,
                tabPositions,
                modifier = Modifier.wrapContentWidth()
            )
        },
    ) {
        tabNames.forEachIndexed { i, title ->
            Tab(
                selected = selectedTabIndex == i,
                onClick = { onClick(i) },
                modifier = Modifier.wrapContentWidth(),
                text = {
                    DetailTabContent(
                        tabName = title,
                        isSelected = selectedTabIndex == i,
                        modifier = Modifier.wrapContentWidth()
                    )
                },
            )
        }
    }
}

@Composable
fun DetailTabContent(tabName: String, isSelected: Boolean, modifier: Modifier = Modifier) {
    Text(
        text = tabName,
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
        fontSize = 14.sp,
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun DetailTabIndicator(
    selectedTabIndex: Int,
    tabPositions: List<TabPosition>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .tabIndicatorOffset(tabPositions[selectedTabIndex])
            .height(4.dp)
            .background(color = MaterialTheme.colors.primary)
    )
}

@Composable
@Preview
fun DetailTabContentPreview() {
    DetailTabContent(tabName = "All Teams", isSelected = true)
}

@Composable
@Preview
fun DetailTabContentPreview2() {
    DetailTabContent(tabName = "All Matches", isSelected = false)
}

@Composable
@Preview(device = Devices.NEXUS_5)
fun DetailTabRowPreview() {
    XLeagueTheme {
        HomeTabRow(listOf("All Teams", "All Matches"), 0, modifier = Modifier.wrapContentWidth())
    }
}

@Composable
@Preview(device = Devices.NEXUS_5)
fun DetailTabRowPreview2() {
    XLeagueTheme(isDarkTheme = true) {
        HomeTabRow(listOf("All Teams", "All Matches"), 0, modifier = Modifier.wrapContentWidth())
    }
}

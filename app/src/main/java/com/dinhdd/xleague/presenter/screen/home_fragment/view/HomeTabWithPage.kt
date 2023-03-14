package com.dinhdd.xleague.presenter.screen.home_fragment.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dinhdd.xleague.presenter.screen.home_fragment.HomeContract

@Composable
fun HomeTabWithPage(
    detailPages: List<HomeContract.HomeTab>,
    selectedPage: HomeContract.HomeTab,
    onPageChanged: (HomeContract.HomeTab) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        HomeTabRow(
            tabNames = detailPages.map { tab -> stringResource(id = tab.titleRes) },
            selectedTabIndex = detailPages.indexOf(selectedPage),
            onClick = { index -> onPageChanged(detailPages[index]) }
        )
        Spacer(modifier = Modifier.size(18.dp))
        selectedPage.content()
    }

}


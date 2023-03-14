package com.dinhdd.xleague.presenter.screen.home_fragment

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dinhdd.xleague.presenter.screen.home_fragment.view.HomeTabWithPage

@Composable
fun HomeScreen(viewModel: HomeContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    val pageList = HomeContract.HomeTab.values().toList()

    Row {
        HomeTabWithPage(
            detailPages = pageList,
            selectedPage = state.activeTab,
            onPageChanged = { tab -> viewModel.onTabClick(tab) })
    }
}
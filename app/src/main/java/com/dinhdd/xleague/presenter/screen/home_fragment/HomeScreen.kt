package com.dinhdd.xleague.presenter.screen.home_fragment

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dinhdd.xleague.presenter.XLeagueNavHost
import com.dinhdd.xleague.presenter.navigateSingleTopTo
import com.dinhdd.xleague.presenter.screen.home_fragment.view.HomeTabWithPage

@Composable
fun HomeScreen(viewModel: HomeContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    val pageList = HomeContract.HomeTab.values().toList()
    val navController = rememberNavController()

    Scaffold(topBar = {
        HomeTabWithPage(
            detailPages = pageList,
            selectedPage = state.activeTab,
            onPageChanged = { tab ->
                navController.navigateSingleTopTo(tab.route)
                viewModel.onTabClick(tab)
            })
    }) { innerPadding ->
        XLeagueNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeScreen() {
    val viewModel: HomeContract.ViewModel = viewModel<HomeViewModel>()
    HomeScreen(viewModel)
}
package com.dinhdd.xleague.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingScreen
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingViewModel
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingContract
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingScreen
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingViewModel

@Composable
fun XLeagueNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = XLeagueScreen.TeamListing.name,
        modifier = modifier
    ) {
        composable(route = XLeagueScreen.TeamListing.name) {
            val viewModel: TeamListingViewModel = hiltViewModel()
            when (viewModel.observeEvent().collectAsState(initial = null).value) {
                null -> Unit
                is TeamListingContract.Event.NavigateTeamMatchesListing -> {

                }
            }
            TeamListingScreen(viewModel)
        }
        composable(route = XLeagueScreen.MatchListing.name) {
            val viewModel: MatchListingViewModel = hiltViewModel()
            MatchListingScreen(viewModel)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }
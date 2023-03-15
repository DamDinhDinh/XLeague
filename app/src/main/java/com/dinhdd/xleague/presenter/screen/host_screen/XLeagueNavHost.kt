package com.dinhdd.xleague.presenter.screen.host_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dinhdd.xleague.presenter.screen.home_screen.homeFlow
import com.dinhdd.xleague.presenter.screen.match_highlight.matchHighlightFlow
import com.dinhdd.xleague.presenter.screen.match_listing.matchListingFlow
import com.dinhdd.xleague.presenter.screen.matches_of_team.matchOfTeamFlow
import com.dinhdd.xleague.presenter.screen.team_listing.teamListingFlow

@Composable
fun XLeagueNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = XLeagueDestination.HomeScreen.name,
        modifier = modifier
    ) {
        homeFlow(navController)
        teamListingFlow(navController)
        matchListingFlow(navController)
        matchOfTeamFlow(navController)
        matchHighlightFlow(navController)
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
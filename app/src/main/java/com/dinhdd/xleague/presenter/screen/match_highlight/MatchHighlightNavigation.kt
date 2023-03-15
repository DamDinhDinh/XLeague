package com.dinhdd.xleague.presenter.screen.match_highlight

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dinhdd.xleague.presenter.screen.host_screen.XLeagueDestination

fun NavGraphBuilder.matchHighlightFlow(navController: NavHostController) {
    composable(route = "${XLeagueDestination.MatchHighlight.name}/{highlightUrl}", arguments = listOf(
        navArgument(name = "highlightUrl") { type = NavType.StringType }
    )) { navBackStackEntry ->
        val highlightUrl = navBackStackEntry.arguments?.getString("highlightUrl") ?: ""
        MatchHighlightScreen(url = highlightUrl)
    }
}
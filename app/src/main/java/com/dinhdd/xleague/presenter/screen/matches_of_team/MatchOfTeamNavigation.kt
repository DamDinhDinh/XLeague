package com.dinhdd.xleague.presenter.screen.matches_of_team

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dinhdd.xleague.presenter.screen.host_screen.XLeagueDestination
import com.dinhdd.xleague.presenter.screen.host_screen.navigateSingleTopTo
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavGraphBuilder.matchOfTeamFlow(navController: NavHostController) {
    composable(
        route = "${XLeagueDestination.MatchOfTeam.name}/{teamId}",
        arguments = listOf(navArgument(name = "teamId") { type = NavType.StringType })
    ) { navBackStackEntry ->
        val teamId = navBackStackEntry.arguments?.getString("teamId") ?: ""
        val viewModel: MatchesOfTeamViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            viewModel.observeEvent().collect {
                when (it) {
                    is MatchesOfTeamContract.Event.NavigateMatchHighlight -> {
                        val matchUrlEncoded = URLEncoder.encode(it.match.highlightsUrl, StandardCharsets.UTF_8.name())
                        navController.navigateSingleTopTo("${XLeagueDestination.MatchHighlight.name}/${matchUrlEncoded}")
                    }
                    else -> Unit
                }
            }
        }
        MatchesOfTeamScreen(viewModel, teamId)
    }
}
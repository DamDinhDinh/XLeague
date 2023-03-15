package com.dinhdd.xleague.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.text.htmlEncode
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dinhdd.xleague.presenter.screen.match_highlight.MatchHighLightScreen
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingContract
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingScreen
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingViewModel
import com.dinhdd.xleague.presenter.screen.matches_of_team.MatchesOfTeamScreen
import com.dinhdd.xleague.presenter.screen.matches_of_team.MatchesOfTeamViewModel
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingContract
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingScreen
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingViewModel
import okio.ByteString.Companion.encode
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun XLeagueNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = XLeagueScreen.TeamListing.name,
        modifier = modifier
    ) {
        composable(route = XLeagueScreen.TeamListing.name) {
            val viewModel: TeamListingViewModel = hiltViewModel()
            when (val state = viewModel.observeEvent().collectAsState(initial = null).value) {
                null -> Unit
                is TeamListingContract.Event.NavigateTeamMatchesListing -> {
                    navController.navigateSingleTopTo("${XLeagueScreen.MatchOfTeam.name}/${state.teamId}")
                }
            }
            TeamListingScreen(viewModel)
        }
        composable(route = XLeagueScreen.MatchListing.name) {
            val viewModel: MatchListingViewModel = hiltViewModel()

            when (val state = viewModel.observeEvent().collectAsState(initial = null).value) {
                null -> Unit
                is MatchListingContract.Event.NavigateMatchHighlight -> {
                    val matchUrlEncoded = URLEncoder.encode(state.match.highlightsUrl, StandardCharsets.UTF_8.name())
                    navController.navigateSingleTopTo("${XLeagueScreen.MatchHighlight.name}/${matchUrlEncoded}")
                }
            }

            MatchListingScreen(viewModel)
        }
        composable(
            route = "${XLeagueScreen.MatchOfTeam.name}/{teamId}",
            arguments = listOf(navArgument(name = "teamId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val teamId = navBackStackEntry.arguments?.getString("teamId") ?: ""
            val viewModel: MatchesOfTeamViewModel = hiltViewModel()
            MatchesOfTeamScreen(viewModel, teamId)
        }
        composable(route = "${XLeagueScreen.MatchHighlight.name}/{highlightUrl}", arguments = listOf(
            navArgument(name = "highlightUrl") { type = NavType.StringType }
        )) { navBackStackEntry ->
            val highlightUrl = navBackStackEntry.arguments?.getString("highlightUrl") ?: ""
            MatchHighLightScreen(url = highlightUrl)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
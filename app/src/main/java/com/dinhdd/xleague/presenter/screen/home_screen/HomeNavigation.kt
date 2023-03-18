package com.dinhdd.xleague.presenter.screen.home_screen

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dinhdd.xleague.presenter.screen.host_screen.XLeagueDestination
import com.dinhdd.xleague.presenter.screen.host_screen.navigateSingleTopTo
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavGraphBuilder.homeFlow(navController: NavHostController) {
    composable(route = XLeagueDestination.HomeScreen.name) {
        val viewModel: HomeViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            viewModel.observeEvent().collect {
                when (it) {
                    is HomeContract.Event.NavigateMatchHighlight -> {
                        val matchUrlEncoded =
                            URLEncoder.encode(it.match.highlightsUrl, StandardCharsets.UTF_8.name())
                        navController.navigateSingleTopTo("${XLeagueDestination.MatchHighlight.name}/${matchUrlEncoded}")
                    }
                    is HomeContract.Event.NavigateMatchOfTeam -> {
                        navController.navigateSingleTopTo("${XLeagueDestination.MatchOfTeam.name}/${it.team.id}")
                    }
                    is HomeContract.Event.NavigateTeamListing -> {
                        navController.navigateSingleTopTo(XLeagueDestination.TeamListing.name)
                    }
                    is HomeContract.Event.NavigateMatchListing -> {
                        navController.navigateSingleTopTo(XLeagueDestination.MatchListing.name)
                    }
                    else -> Unit
                }
            }
        }

        HomeScreen(viewModel)
    }
}
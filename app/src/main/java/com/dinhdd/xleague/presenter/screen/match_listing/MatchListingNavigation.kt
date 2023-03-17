package com.dinhdd.xleague.presenter.screen.match_listing

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dinhdd.xleague.presenter.screen.host_screen.XLeagueDestination
import com.dinhdd.xleague.presenter.screen.host_screen.navigateSingleTopTo
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavGraphBuilder.matchListingFlow(navController: NavHostController) {
    composable(route = XLeagueDestination.MatchListing.name) {
        val viewModel: MatchListingViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            viewModel.observeEvent().collect {
                when (it) {
                    is MatchListingContract.Event.NavigateMatchHighlight -> {
                        val matchUrlEncoded = URLEncoder.encode(it.match.highlightsUrl, StandardCharsets.UTF_8.name())
                        navController.navigateSingleTopTo("${XLeagueDestination.MatchHighlight.name}/${matchUrlEncoded}")
                    }
                    else -> Unit
                }
            }
        }

        MatchListingScreen(viewModel)
    }
}
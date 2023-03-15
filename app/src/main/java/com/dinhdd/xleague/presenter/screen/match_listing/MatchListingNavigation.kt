package com.dinhdd.xleague.presenter.screen.match_listing

import androidx.compose.runtime.collectAsState
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

        when (val state = viewModel.observeEvent().collectAsState(initial = null).value) {
            is MatchListingContract.Event.NavigateMatchHighlight -> {
                val matchUrlEncoded = URLEncoder.encode(state.match.highlightsUrl, StandardCharsets.UTF_8.name())
                navController.navigateSingleTopTo("${XLeagueDestination.MatchHighlight.name}/${matchUrlEncoded}")
            }
            else -> Unit
        }

        MatchListingScreen(viewModel)
    }
}
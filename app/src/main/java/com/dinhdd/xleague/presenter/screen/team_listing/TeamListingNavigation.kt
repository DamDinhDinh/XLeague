package com.dinhdd.xleague.presenter.screen.team_listing

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dinhdd.xleague.presenter.screen.host_screen.XLeagueDestination
import com.dinhdd.xleague.presenter.screen.host_screen.navigateSingleTopTo

fun NavGraphBuilder.teamListingFlow(navController: NavHostController){
    composable(route = XLeagueDestination.TeamListing.name) {
        val viewModel: TeamListingViewModel = hiltViewModel()
        when (val state = viewModel.observeEvent().collectAsState(initial = null).value) {
            null -> Unit
            is TeamListingContract.Event.NavigateTeamMatchesListing -> {
                navController.navigateSingleTopTo("${XLeagueDestination.MatchOfTeam.name}/${state.teamId}")
            }
        }
        TeamListingScreen(viewModel)
    }
}
package com.dinhdd.xleague.presenter.screen.team_listing

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dinhdd.xleague.presenter.screen.host_screen.XLeagueDestination
import com.dinhdd.xleague.presenter.screen.host_screen.navigateSingleTopTo

fun NavGraphBuilder.teamListingFlow(navController: NavHostController){
    composable(route = XLeagueDestination.TeamListing.name) {
        val viewModel: TeamListingViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            viewModel.observeEvent().collect {
                when (it) {
                    is TeamListingContract.Event.NavigateTeamMatchesListing -> {
                        navController.navigateSingleTopTo("${XLeagueDestination.MatchOfTeam.name}/${it.teamId}")
                    }
                    else -> Unit
                }
            }
        }
        TeamListingScreen(viewModel)
    }
}
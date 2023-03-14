package com.dinhdd.xleague.presenter.screen.team_listing

import com.dinhdd.xleague.presenter.model.TeamPresent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface TeamListingContract {
    interface ViewModel {
        fun fetchAllTeams()

        fun onTeamClick(team: TeamPresent)

        fun observeViewState(): StateFlow<ViewState?>

        fun observeEvent(): SharedFlow<Event>
    }

    data class ViewState(val teams: List<TeamPresent>)

    sealed class Event {
        data class NavigateTeamMatchesListing(val teamId: String) : Event()
    }
}
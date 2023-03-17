package com.dinhdd.xleague.presenter.screen.home_screen

import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.model.TeamPresent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    interface ViewModel {

        fun fetchData()

        fun onTeamClick(team: TeamPresent)

        fun onMatchClick(match: MatchPresent)

        fun onTeamListingClick()

        fun onMatchListingClick()

        fun observeViewState(): StateFlow<ViewState?>

        fun observeEvent(): SharedFlow<Event>
    }

    data class ViewState(
        val teams: List<TeamPresent> = emptyList(),
        val previousMatches: List<MatchPresent> = emptyList(),
        val upcomingMatches: List<MatchPresent> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed class Event {
        data class NavigateMatchHighlight(val match: MatchPresent) : Event()

        data class CreateMatchStartingNotification(val match: MatchPresent) : Event()

        data class NavigateMatchOfTeam(val team: TeamPresent) : Event()

        object NavigateTeamListing : Event()

        object NavigateMatchListing : Event()
    }
}
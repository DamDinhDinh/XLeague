package com.dinhdd.xleague.presenter.screen.matches_of_team

import com.dinhdd.xleague.presenter.model.MatchPresent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MatchesOfTeamContract {
    interface ViewModel {
        fun fetchMatchOfTeam(teamId: String)

        fun onMatchClick(match: MatchPresent)

        fun observeViewState(): StateFlow<ViewState?>

        fun observeEvent(): SharedFlow<Event>
    }

    data class ViewState(val matches: List<MatchPresent>)

    sealed class Event {
        data class NavigateMatchHighlight(val match: MatchPresent) : Event()

        data class CreateMatchStartingNotification(val match: MatchPresent) : Event()
    }
}
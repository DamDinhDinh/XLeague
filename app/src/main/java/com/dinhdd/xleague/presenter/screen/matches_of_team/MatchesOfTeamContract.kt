package com.dinhdd.xleague.presenter.screen.matches_of_team

import com.dinhdd.xleague.presenter.model.MatchPresent
import kotlinx.coroutines.flow.StateFlow

interface MatchesOfTeamContract {
    interface ViewModel {
        fun fetchMatchOfTeam(teamId: String)

        fun observeViewState(): StateFlow<ViewState?>
    }

    data class ViewState(val matches: List<MatchPresent>)
}
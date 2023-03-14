package com.dinhdd.xleague.presenter.screen.match_listing

import com.dinhdd.xleague.presenter.model.MatchPresent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MatchListingContract {
    interface ViewModel {
        fun fetchAllMatches()

        fun observeViewState(): StateFlow<ViewState?>
    }

    data class ViewState(val matches: List<MatchPresent>)

}
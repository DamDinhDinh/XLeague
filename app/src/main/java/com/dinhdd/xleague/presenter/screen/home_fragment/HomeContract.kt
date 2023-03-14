package com.dinhdd.xleague.presenter.screen.home_fragment

import androidx.annotation.StringRes
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.XLeagueScreen
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    interface ViewModel {
        fun onTabClick(tab: HomeTab)

        fun observeViewState(): StateFlow<ViewState>
    }

    data class ViewState(val activeTab: HomeTab)

    enum class HomeTab(@StringRes val titleRes: Int, val route: String) {
        TEAM_LISTING(R.string.all_team_screen_title, route = XLeagueScreen.TeamListing.name),
        MATCH_LISTING(R.string.all_match_screen_title, route = XLeagueScreen.MatchListing.name)
    }
}
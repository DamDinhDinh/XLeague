package com.dinhdd.xleague.presenter.screen.home_fragment

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.screen.match_listing.MatchListingScreen
import com.dinhdd.xleague.presenter.screen.team_listing.TeamListingScreen
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    interface ViewModel {
        fun onTabClick(tab: HomeTab)

        fun observeViewState(): StateFlow<ViewState>
    }

    data class ViewState(val activeTab: HomeTab)

    enum class HomeTab(@StringRes val titleRes: Int, val content: @Composable () -> Unit) {

        TEAM_LISTING(R.string.all_team_screen_title, content = {
            TeamListingScreen()
        }),
        MATCH_LISTING(R.string.all_match_screen_title, content = {
            MatchListingScreen()
        })
    }
}
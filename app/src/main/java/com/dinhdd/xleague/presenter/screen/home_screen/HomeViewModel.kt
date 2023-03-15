package com.dinhdd.xleague.presenter.screen.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhdd.domain.usecase.GetAllMatchesUseCase
import com.dinhdd.domain.usecase.GetAllTeamsUseCase
import com.dinhdd.xleague.dispatcher.DispatcherProvider
import com.dinhdd.xleague.presenter.mapper.toPresent
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.model.TeamPresent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTeamsUseCase: GetAllTeamsUseCase,
    private val getAllMatchesUseCase: GetAllMatchesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(), HomeContract.ViewModel {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val viewStateFlow = MutableStateFlow<HomeContract.ViewState?>(null)
    private val eventFlow = MutableSharedFlow<HomeContract.Event>()

    override fun fetchData() {
        viewModelScope.launch {
            viewStateFlow.value = HomeContract.ViewState(emptyList(), emptyList(), true)
            getAllTeamsUseCase()
                .zip(getAllMatchesUseCase()) { teams, matches -> Pair(teams, matches) }
                .flowOn(dispatcherProvider.io)
                .catch { error -> Log.e(TAG, "fetchData: $error") }
                .map { (teams, matches) -> teams.map { it.toPresent() } to matches.map { it.toPresent() } }
                .collect { (teams, matches) ->
                    viewStateFlow.value = HomeContract.ViewState(teams, matches, false)
                }
        }
    }

    override fun onTeamClick(team: TeamPresent) {
        viewModelScope.launch {
            eventFlow.emit(HomeContract.Event.NavigateMatchOfTeam(team))
        }
    }

    override fun onMatchClick(match: MatchPresent) {
        viewModelScope.launch {
            when (match.matchType) {
                MatchPresent.MatchType.Previous -> {
                    eventFlow.emit(HomeContract.Event.NavigateMatchHighlight(match))
                }
                MatchPresent.MatchType.UpComing -> {
                    eventFlow.emit(HomeContract.Event.CreateMatchStartingNotification(match))
                }
                else -> Unit
            }
        }
    }

    override fun onTeamListingClick() {
        viewModelScope.launch {
            eventFlow.emit(HomeContract.Event.NavigateTeamListing)
        }
    }

    override fun onMatchListingClick() {
        viewModelScope.launch {
            eventFlow.emit(HomeContract.Event.NavigateMatchListing)
        }
    }

    override fun observeViewState(): StateFlow<HomeContract.ViewState?> = viewStateFlow.asStateFlow()

    override fun observeEvent(): SharedFlow<HomeContract.Event> = eventFlow.asSharedFlow()
}
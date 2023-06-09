package com.dinhdd.xleague.presenter.screen.team_listing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhdd.domain.usecase.GetAllTeamsUseCase
import com.dinhdd.xleague.dispatcher.DispatcherProvider
import com.dinhdd.xleague.presenter.mapper.toPresent
import com.dinhdd.xleague.presenter.model.TeamPresent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListingViewModel @Inject constructor(
    private val getAllTeamsUseCase: GetAllTeamsUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(),
    TeamListingContract.ViewModel {

    companion object {
        private const val TAG = "TeamListingViewModel"
    }

    private val viewStateFlow = MutableStateFlow<TeamListingContract.ViewState?>(null)
    private val eventFlow = MutableSharedFlow<TeamListingContract.Event>()

    override fun fetchAllTeams() {
        viewModelScope.launch {
            viewStateFlow.value = TeamListingContract.ViewState(isLoading = true)
            getAllTeamsUseCase()
                .flowOn(dispatcherProvider.io)
                .catch { error -> Log.d(TAG, "fetchAllTeams: $error") }
                .map { teamList -> teamList.map { it.toPresent() } }
                .collect { teams ->
                    viewStateFlow.value =
                        viewStateFlow.value?.copy(
                            isLoading = false,
                            teams = teams
                        ) ?: TeamListingContract.ViewState(
                            isLoading = false,
                            teams = teams
                        )
                }
        }
    }

    override fun onTeamClick(team: TeamPresent) {
        viewModelScope.launch {
            eventFlow.emit(TeamListingContract.Event.NavigateTeamMatchesListing(team.id))
        }
    }

    override fun observeViewState(): StateFlow<TeamListingContract.ViewState?> = viewStateFlow.asStateFlow()

    override fun observeEvent(): SharedFlow<TeamListingContract.Event> = eventFlow.asSharedFlow()
}
package com.dinhdd.xleague.presenter.screen.matches_of_team

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhdd.domain.usecase.GetAllMatchesOfTeamUseCase
import com.dinhdd.xleague.presenter.mapper.toPresent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesOfTeamViewModel @Inject constructor(private val getAllMatchesOfTeamUseCase: GetAllMatchesOfTeamUseCase) :
    ViewModel(), MatchesOfTeamContract.ViewModel {

    companion object {
        private const val TAG = "MatchesOfTeamViewModel"
    }

    private val viewStateFlow = MutableStateFlow<MatchesOfTeamContract.ViewState?>(null)

    override fun fetchMatchOfTeam(teamId: String) {
        viewModelScope.launch {
            getAllMatchesOfTeamUseCase(teamId)
                .flowOn(Dispatchers.IO)
                .catch { error -> Log.e(TAG, "fetchMatchOfTeam: $error") }
                .map { matchList -> matchList.map { it.toPresent() } }
                .collect { matches ->
                    viewStateFlow.value =
                        viewStateFlow.value?.copy(matches = matches) ?: MatchesOfTeamContract.ViewState(matches)
                }
        }
    }

    override fun observeViewState(): StateFlow<MatchesOfTeamContract.ViewState?> = viewStateFlow.asStateFlow()
}
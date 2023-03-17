package com.dinhdd.xleague.presenter.screen.match_listing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhdd.domain.usecase.GetAllMatchesUseCase
import com.dinhdd.xleague.dispatcher.DispatcherProvider
import com.dinhdd.xleague.presenter.mapper.toPresent
import com.dinhdd.xleague.presenter.model.MatchPresent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListingViewModel @Inject constructor(
    private val getAllMatchesUseCase: GetAllMatchesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(),
    MatchListingContract.ViewModel {

    companion object {
        private const val TAG = "MatchListingViewModel"
    }

    private val viewStateFlow = MutableStateFlow<MatchListingContract.ViewState?>(null)
    private val eventFlow = MutableSharedFlow<MatchListingContract.Event>()

    override fun fetchAllMatches() {
        viewModelScope.launch {
            viewStateFlow.value = MatchListingContract.ViewState(isLoading = true)
            getAllMatchesUseCase()
                .flowOn(dispatcherProvider.io)
                .catch { error -> Log.e(TAG, "fetchAllMatches: $error") }
                .map { matchList -> matchList.map { it.toPresent() } }
                .collect { matches ->
                    viewStateFlow.value =
                        viewStateFlow.value?.copy(
                            isLoading = false,
                            previousMatches = matches.filter { MatchPresent.MatchType.Previous == it.matchType },
                            upcomingMatches = matches.filter { MatchPresent.MatchType.UpComing == it.matchType },
                        ) ?: MatchListingContract.ViewState(
                            isLoading = false,
                            previousMatches = matches.filter { MatchPresent.MatchType.Previous == it.matchType },
                            upcomingMatches = matches.filter { MatchPresent.MatchType.UpComing == it.matchType },
                        )
                }
        }
    }

    override fun onMatchClick(match: MatchPresent) {
        viewModelScope.launch {
            when (match.matchType) {
                MatchPresent.MatchType.Previous -> {
                    eventFlow.emit(MatchListingContract.Event.NavigateMatchHighlight(match))
                }
                MatchPresent.MatchType.UpComing -> {
                    eventFlow.emit(MatchListingContract.Event.CreateMatchStartingNotification(match))
                }
                else -> Unit
            }
        }
    }

    override fun observeViewState(): StateFlow<MatchListingContract.ViewState?> = viewStateFlow.asStateFlow()

    override fun observeEvent(): SharedFlow<MatchListingContract.Event> = eventFlow.asSharedFlow()
}
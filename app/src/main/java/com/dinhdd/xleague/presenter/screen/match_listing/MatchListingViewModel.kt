package com.dinhdd.xleague.presenter.screen.match_listing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhdd.domain.usecase.GetAllMatchesUseCase
import com.dinhdd.xleague.presenter.mapper.toPresent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListingViewModel @Inject constructor(private val getAllMatchesUseCase: GetAllMatchesUseCase) : ViewModel(),
    MatchListingContract.ViewModel {

    companion object {
        private const val TAG = "MatchListingViewModel"
    }

    private val viewStateFlow = MutableStateFlow<MatchListingContract.ViewState?>(null)

    override fun fetchAllMatches() {
        viewModelScope.launch {
            getAllMatchesUseCase()
                .flowOn(Dispatchers.IO)
                .catch { error -> Log.e(TAG, "fetchAllMatches: $error") }
                .map { matchList -> matchList.map { it.toPresent() } }
                .collect { matches ->
                    viewStateFlow.value =
                        viewStateFlow.value?.copy(matches = matches) ?: MatchListingContract.ViewState(matches)
                }
        }
    }

    override fun observeViewState(): StateFlow<MatchListingContract.ViewState?> = viewStateFlow.asStateFlow()
}
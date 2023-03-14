package com.dinhdd.xleague.presenter.screen.home_fragment

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(), HomeContract.ViewModel {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val viewStateFlow = MutableStateFlow(HomeContract.ViewState(HomeContract.HomeTab.TEAM_LISTING))

    override fun onTabClick(tab: HomeContract.HomeTab) {
        viewStateFlow.value = viewStateFlow.value.copy(activeTab = tab)
    }

    override fun observeViewState(): StateFlow<HomeContract.ViewState> = viewStateFlow.asStateFlow()
}
package com.dinhdd.xleague.presenter.screen.team_listing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamListingFragment: Fragment() {

    private val TAG = "TeamListingFragment"

    private val viewModel: TeamListingContract.ViewModel by viewModels<TeamListingViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e(TAG, "onCreateView: ")
        return ComposeView(requireContext()).apply {
            setContent {
                TeamListingScreen(viewModel = viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
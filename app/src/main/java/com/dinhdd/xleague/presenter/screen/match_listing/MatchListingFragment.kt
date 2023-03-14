package com.dinhdd.xleague.presenter.screen.match_listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchListingFragment : Fragment() {
    private val viewModel: MatchListingContract.ViewModel by viewModels<MatchListingViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MatchListingScreen(viewModel = viewModel)
            }
        }
    }
}
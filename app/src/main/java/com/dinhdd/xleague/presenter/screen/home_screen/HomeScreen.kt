package com.dinhdd.xleague.presenter.screen.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.dinhdd.xleague.presenter.screen.common.ScreenConstant.MEDIUM_SCREEN_WIDTH
import com.dinhdd.xleague.presenter.screen.home_screen.view.LargeScreenHomeContent
import com.dinhdd.xleague.presenter.screen.home_screen.view.SmallScreenHomeContent
import com.dinhdd.xleague.presenter.util.NotificationUtils

@Composable
fun HomeScreen(viewModel: HomeContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchData()
        }
        viewModel.observeEvent().collect {
            when (it) {
                is HomeContract.Event.CreateMatchStartingNotification -> {
                    NotificationUtils.scheduleMatchStartingNotification(it.match, context)
                }
                else -> Unit
            }
        }
    }

    state?.let {
        if (it.isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            BoxWithConstraints {
                when {
                    maxWidth > MEDIUM_SCREEN_WIDTH -> {
                        LargeScreenHomeContent(viewModel = viewModel)
                    }
                    else -> {
                        SmallScreenHomeContent(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
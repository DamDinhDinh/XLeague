package com.dinhdd.xleague.presenter.screen.home_screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.dinhdd.xleague.presenter.screen.common.ScreenConstant.MEDIUM_SCREEN_WIDTH
import com.dinhdd.xleague.presenter.util.NotificationUtils

@Composable
fun HomeScreen(viewModel: HomeContract.ViewModel) {
    val state by viewModel.observeViewState().collectAsState()
    val eventState by viewModel.observeEvent().collectAsState(initial = null)
    LaunchedEffect(Unit) {
        if (viewModel.observeViewState().value == null) {
            viewModel.fetchData()
        }
    }

    state?.let {
        when {
            it.isLoading -> {

            }
            else -> {
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

    when (val event = eventState) {
        is HomeContract.Event.CreateMatchStartingNotification -> {
            NotificationUtils.scheduleMatchStartingNotification(event.match, LocalContext.current)
        }
        else -> Unit
    }
}
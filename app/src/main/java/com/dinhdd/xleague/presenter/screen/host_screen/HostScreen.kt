package com.dinhdd.xleague.presenter.screen.host_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dinhdd.xleague.presenter.screen.host_screen.view.AppBar

@Composable
fun HostScreen() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = XLeagueDestination.fromRoute(backStackEntry.value?.destination?.route)

    Scaffold(
        topBar = {
            AppBar(
                title = currentScreen.name,
                onBackClick = { navController.popBackStack() },
                isShowIcon = currentScreen != XLeagueDestination.HomeScreen
            )
        }) { innerPadding ->
        XLeagueNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

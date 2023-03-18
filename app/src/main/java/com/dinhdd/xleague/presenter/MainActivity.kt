package com.dinhdd.xleague.presenter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.dinhdd.xleague.presenter.screen.host_screen.HostScreen
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme
import com.dinhdd.xleague.presenter.util.NotificationUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            XLeagueTheme {
                HostScreen()
            }
        }

        NotificationUtils.createMatchStartingNotificationChannel(context = this)
    }
}
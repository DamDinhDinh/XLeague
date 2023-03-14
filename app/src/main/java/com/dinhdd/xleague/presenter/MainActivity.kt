package com.dinhdd.xleague.presenter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.dinhdd.xleague.R
import com.dinhdd.xleague.presenter.screen.home_fragment.HomeScreen
import com.dinhdd.xleague.presenter.screen.theme.XLeagueTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XLeagueTheme {
                HomeScreen()
            }
        }
    }
}
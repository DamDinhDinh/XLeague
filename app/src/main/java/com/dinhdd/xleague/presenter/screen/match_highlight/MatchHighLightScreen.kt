package com.dinhdd.xleague.presenter.screen.match_highlight

import androidx.compose.runtime.Composable
import com.dinhdd.xleague.presenter.screen.common.VideoView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun MatchHighLightScreen(url: String) {
    val urlDecoded = URLDecoder.decode(url, StandardCharsets.UTF_8.name())
    VideoView(urlDecoded)
}
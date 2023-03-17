package com.dinhdd.xleague.presenter.screen.match_highlight

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dinhdd.xleague.presenter.screen.common.VideoView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun MatchHighlightScreen(url: String) {
    val urlDecoded = URLDecoder.decode(url, StandardCharsets.UTF_8.name())

    Box(contentAlignment = Alignment.Center) {
        VideoView(
            modifier = Modifier.align(Alignment.Center),
            url = urlDecoded
        )
    }
}
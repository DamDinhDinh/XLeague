package com.dinhdd.xleague.presenter.screen.match_highlight

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dinhdd.xleague.presenter.screen.common.VideoView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun MatchHighlightScreen(url: String) {
    val urlDecoded = URLDecoder.decode(url, StandardCharsets.UTF_8.name())

    Row {
        VideoView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    4.div(3).toFloat()
                ), url = urlDecoded
        )
    }
}
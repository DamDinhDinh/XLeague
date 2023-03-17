package com.dinhdd.xleague.presenter.screen.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource


@Composable
fun VideoView(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(url))

                setMediaSource(source)
                playWhenReady = true
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
                repeatMode = Player.REPEAT_MODE_OFF
                prepare()
            }
    }

    DisposableEffect(
        AndroidView(modifier = modifier.fillMaxSize(),
            factory = {
                StyledPlayerView(context).apply {
                    showController()
                    controllerHideOnTouch = true
                    controllerAutoShow = true
                    controllerShowTimeoutMs = 3000
                    exoPlayer.addListener(object : Player.Listener {
                        override fun onPlaybackStateChanged(playbackState: Int) {
                            when (playbackState) {
                                Player.STATE_ENDED -> {
                                    showController()
                                }
                                Player.STATE_READY -> {
                                    hideController()
                                }//*Hide progressbar!
                                Player.STATE_BUFFERING -> {
                                    showController()
                                } //*Show progressbar!
                                Player.STATE_IDLE -> {
                                    showController()
                                }
                            }
                        }
                    })
                    player = exoPlayer
                }
            })
    ) {
        onDispose { exoPlayer.release() }
    }
}
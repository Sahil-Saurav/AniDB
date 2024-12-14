package com.example.anidb.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun Youtube_Player(
    videoId:String
){
    val ctx = LocalContext.current
    AndroidView(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 4.dp,
                end = 4.dp),
        factory = {
            var view = YouTubePlayerView(it)
            val fragment = view.addYouTubePlayerListener(
                object:AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer){
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(
                            videoId = videoId,
                            startSeconds = 0f
                        )
                    }
                }
            )
            view
        }
    )
}
package com.arthur.tmdb.ui.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource


/*@Composable
fun VideoPlayer() {
    // This is the official way to access current context from Composable functions
    val context = LocalContext.current

    // Do not recreate the player everytime this Composable commits
    val exoPlayer = remember(context) {
        SimpleExoPlayer.Builder(context).build().apply {
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.packageName))

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(
                    Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                ))

            this.prepare(source)
        }
    }
}*/

@Composable
fun VideoPlayer() {
    val videoURI = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    val httpDataSourceFactory: HttpDataSource.Factory = DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(false)
    val dataSourceFactory: DataSource.Factory = DataSource.Factory {
        val dataSource = httpDataSourceFactory.createDataSource()
        dataSource.setRequestProperty(
            "cookie", "cookieValue"
        )
        dataSource.setRequestProperty("Range", "1-10000")
        dataSource
    }

    val mContext = LocalContext.current
    // Initializing ExoPLayer
    val mExoPlayer = remember(mContext) {
        ExoPlayer.Builder(mContext)
            .setMediaSourceFactory(DefaultMediaSourceFactory(dataSourceFactory)).build().apply {

                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(videoURI))
                    .build()
                setMediaItem(mediaItem)
                playWhenReady = true
                prepare()

            }
    }

    DisposableEffect(
        // Implementing ExoPlayer
        AndroidView(factory = { context ->
            StyledPlayerView(context).apply {
                player = mExoPlayer
            }
        })
    ) {
        onDispose {
            mExoPlayer.release()
        }
    }
}
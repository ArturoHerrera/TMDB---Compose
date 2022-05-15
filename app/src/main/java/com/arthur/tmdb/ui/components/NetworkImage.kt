package com.arthur.tmdb.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.arthur.tmdb.R
import com.arthur.tmdb.ui.theme.shimmerHighLight
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette
import kotlin.random.Random

/**
 * A wrapper around [CoilImage] setting a default [contentScale] and showing
 * an indicator when loading disney poster images.
 *
 * @see CoilImage https://github.com/skydoves/landscapist#coil
 */
@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    circularRevealedEnabled: Boolean = false,
    contentScale: ContentScale = ContentScale.Crop,
    bitmapPalette: BitmapPalette? = null
) {
    CoilImage(
        imageModel = url,
        modifier = modifier,
        contentScale = contentScale,
        circularReveal = CircularReveal(duration = 250),
        bitmapPalette = bitmapPalette,
        previewPlaceholder = R.drawable.ic_local_play,
        shimmerParams = ShimmerParams(
            baseColor = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
            highlightColor = shimmerHighLight,
            dropOff = 0.65f
        ),
        failure = {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ic_local_play),
                    contentDescription = null
                )
            }
        },
    )
}

@Preview
@Composable
fun PreviewNetworkImage() {
    NetworkImage(url = "https://image.tmdb.org/t/p/original/wigZBAmNrIhxp2FNGOROUAeHvdh.jpg")
}
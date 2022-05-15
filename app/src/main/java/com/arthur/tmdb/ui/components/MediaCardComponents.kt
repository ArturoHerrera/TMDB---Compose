package com.arthur.tmdb.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arthur.tmdb.R
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.ui.theme.SuperWhite
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MediaHorizontalListComponent(mediaList: List<MediaItem>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Playing Now Movies",
            modifier = Modifier.padding(16.dp, 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SuperWhite
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                top = 8.dp,
                start = 8.dp,
                end = 16.dp,
                bottom = 65.dp
            )
        ) {
            items(mediaList) { media ->
                MediaCardComponent(
                    mItem = media,
                    onMediaClick = {_,_ -> }
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MediaCardComponent(mItem: MediaItem, onMediaClick: (Long, String) -> Unit) {
    Card(
        backgroundColor = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
        modifier = Modifier.size(140.dp, 210.dp),
        elevation = 8.dp,
        onClick = { onMediaClick(mItem.id, "movie") }
    ) {
        Column() {
            NetworkImage(
                modifier = Modifier
                    .aspectRatio(0.8f),
                url = mItem.getImageUrl(),
            )
            Box() {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Black.copy(alpha = 0.7f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = mItem.title,
                            fontSize = 10.sp,
                            style = MaterialTheme.typography.h6,
                            color = SuperWhite,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(start = 0.dp, end = 0.dp, top = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }
    }
}
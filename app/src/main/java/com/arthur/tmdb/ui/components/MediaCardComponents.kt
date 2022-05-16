package com.arthur.tmdb.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.ui.theme.SuperWhite
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MediaHorizontalListComponent(
    sectionTitle: String,
    mediaList: List<MediaItem>,
    onMediaClick: (Long) -> Unit,
    onSeeMore: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = sectionTitle,
                modifier = Modifier.padding(16.dp, 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = SuperWhite
            )
            IconButton(onClick = onSeeMore) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                top = 0.dp,
                start = 8.dp,
                end = 16.dp,
                bottom = 24.dp
            )
        ) {
            items(mediaList) { media ->
                MediaCardComponent(
                    mItem = media,
                    onMediaClick = { onMediaClick(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun GridMoviesComponent(mediaList: List<MediaItem>, onMediaClick: (Long) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(mediaList) { item ->
            MediaCardGridComponent(
                mItem = item,
                onMediaClick = { }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MediaCardComponent(mItem: MediaItem, onMediaClick: (Long) -> Unit) {
    Card(
        backgroundColor = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
        modifier = Modifier.size(140.dp, 210.dp),
        elevation = 8.dp,
        onClick = { onMediaClick(mItem.id) }
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
                        verticalArrangement = Arrangement.Center
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

@ExperimentalMaterialApi
@Composable
fun MediaCardGridComponent(mItem: MediaItem, onMediaClick: (Long) -> Unit) {
    Card(
        backgroundColor = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
        modifier = Modifier
            .size(140.dp, 210.dp)
            .padding(8.dp),
        elevation = 8.dp,
        onClick = { onMediaClick(mItem.id) }
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
                        verticalArrangement = Arrangement.Center
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
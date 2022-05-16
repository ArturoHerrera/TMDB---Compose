package com.arthur.tmdb.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arthur.tmdb.R
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.data.models.MovieItem
import com.arthur.tmdb.ui.theme.SuperWhite
import kotlin.random.Random

@ExperimentalMaterialApi
@Composable
fun DetailMovieComponent(mItem: MovieItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                backgroundColor = Color(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                ),
                modifier = Modifier.size(170.dp, 250.dp),
                elevation = 8.dp
            ) {
                Column() {
                    NetworkImage(
                        url = mItem.getImageUrl(),
                    )
                }
            }
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 0.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
            ) {
                Text(
                    text = stringResource(id = R.string.movie_release_date),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.h6,
                    color = SuperWhite,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = mItem.releaseDate,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.h6,
                    color = SuperWhite,
                    fontWeight = FontWeight.ExtraLight,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 0.dp,
                            end = 0.dp,
                            top = 8.dp,
                            bottom = 0.dp
                        )
                )
                Text(
                    text = stringResource(id = R.string.movie_vote_Average),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.h6,
                    color = SuperWhite,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 0.dp,
                            end = 0.dp,
                            top = 16.dp,
                            bottom = 0.dp
                        )
                )
                Text(
                    text = mItem.voteAverage.toString(),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.h6,
                    color = SuperWhite,
                    fontWeight = FontWeight.ExtraLight,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 0.dp,
                            end = 0.dp,
                            top = 8.dp,
                            bottom = 0.dp
                        )
                )
            }
        }
        Text(
            text = "Overview: ",
            fontSize = 16.sp,
            style = MaterialTheme.typography.h6,
            color = SuperWhite,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 0.dp,
                    end = 0.dp,
                    top = 8.dp,
                    bottom = 0.dp
                ),
        )

        Text(
            text = mItem.overview ?: "NO DISPONIBLE",
            fontSize = 16.sp,
            style = MaterialTheme.typography.h6,
            color = SuperWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 0.dp,
                    end = 0.dp,
                    top = 8.dp,
                    bottom = 0.dp
                ),
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Start
        )
    }
}
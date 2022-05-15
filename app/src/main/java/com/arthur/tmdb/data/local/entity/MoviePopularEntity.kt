package com.arthur.tmdb.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arthur.tmdb.data.remote.dto.MovieDataDto
import com.arthur.tmdb.data.remote.dto.MovieNowPlayingDto
import com.arthur.tmdb.data.remote.dto.MoviePopularDto

@Entity(tableName = "movie_popular")
data class MoviePopularEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "page") val page: Int,
) {
    constructor(moviePopularDto: MoviePopularDto, movieDataDto: MovieDataDto) : this(
        id = movieDataDto.id?.toLong() ?: 0L,
        title = movieDataDto.title ?: "",
        posterUrl = movieDataDto.posterPath ?: "",
        page = moviePopularDto.page ?: 0
    )
}
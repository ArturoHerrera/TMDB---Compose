package com.arthur.tmdb.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arthur.tmdb.data.remote.dto.MovieDetailDto

@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Float,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "poster_path") val posterPath: String
) {
    constructor(dto: MovieDetailDto): this(
        id = dto.id ?: 0,
        title = dto.title ?: "",
        video = dto.video?: false,
        overview = dto.overview?:"",
        voteAverage = dto.voteAverage?: 0.0F,
        releaseDate = dto.releaseDate?: "",
        posterPath = dto.posterPath?: ""
    )
}
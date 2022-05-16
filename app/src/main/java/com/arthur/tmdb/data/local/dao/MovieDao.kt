package com.arthur.tmdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arthur.tmdb.data.local.entity.MovieDetailEntity
import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity
import com.arthur.tmdb.data.local.entity.MoviePopularEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_playing_now WHERE page = :page")
    fun getPlayingNowMoviesByPage(page: Int): Flow<List<MoviePlayingNowEntity>>

    @Query("SELECT * FROM movie_playing_now ORDER BY RANDOM() LIMIT 20")
    fun getRandomPlayingNowMovies(): Flow<List<MoviePlayingNowEntity>>

    @Query("SELECT * FROM movie_popular ORDER BY RANDOM() LIMIT 20")
    fun getRandomPopularMovies(): Flow<List<MoviePlayingNowEntity>>

    @Query("SELECT * FROM movie_detail WHERE id = :movieId LIMIT 1")
    fun getMovieDetail(movieId: Long): Flow<MovieDetailEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(moviePlayingNowEntity: MoviePlayingNowEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(moviePlayingNowEntity: MoviePopularEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDetail(movieDetailEntity: MovieDetailEntity)

}
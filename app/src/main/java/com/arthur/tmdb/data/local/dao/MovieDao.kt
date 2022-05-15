package com.arthur.tmdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_playing_now ORDER BY RANDOM() LIMIT 20")
    fun getRandomPlayingNowMovies(): Flow<List<MoviePlayingNowEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlayingNowMovie(moviePlayingNowEntity: MoviePlayingNowEntity)

}
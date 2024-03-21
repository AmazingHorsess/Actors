package com.example.actors.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.actors.data.datasource.database.entity.MovieTrackingEntity
@Dao
interface MovieTrackingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTracking(movieTrackingEntity: MovieTrackingEntity)

    @Query("SELECT * FROM movie_tracking_table")
    suspend fun getAllMovieTracking(): List<MovieTrackingEntity>

    @Query("SELECT * FROM movie_tracking_table WHERE id = :id")
    suspend fun getMovieTrackingById(id: Int): MovieTrackingEntity?

    @Query("DELETE FROM movie_tracking_table WHERE id = :id")
    suspend fun deleteMovieTrackingById(id: Int)

}
package com.example.actors.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.actors.data.datasource.database.dao.FavoriteActorsDao
import com.example.actors.data.datasource.database.dao.FavoriteMoviesDao
import com.example.actors.data.datasource.database.dao.MovieTrackingDao
import com.example.actors.data.datasource.database.entity.FavoriteActorsEntity
import com.example.actors.data.datasource.database.entity.FavoriteMoviesEntity


@Database(
    entities = [FavoriteActorsEntity::class,FavoriteMoviesEntity::class],
    version = 1,
    exportSchema = false

)
abstract class AppDatabase : RoomDatabase(){

    abstract val movieTrackingDao: MovieTrackingDao

    abstract val favoriteActorDao: FavoriteActorsDao

    abstract val favoriteMoviesDao: FavoriteMoviesDao
}
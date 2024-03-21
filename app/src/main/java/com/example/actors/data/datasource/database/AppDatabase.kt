package com.example.actors.data.datasource.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.actors.data.datasource.database.dao.FavoriteActorsDao
import com.example.actors.data.datasource.database.dao.FavoriteMoviesDao
import com.example.actors.data.datasource.database.dao.MovieTrackingDao
import com.example.actors.data.datasource.database.entity.FavoriteActorsEntity
import com.example.actors.data.datasource.database.entity.FavoriteMoviesEntity
import com.example.actors.data.datasource.database.entity.MovieTrackingEntity


@Database(
    entities = [FavoriteActorsEntity::class,FavoriteMoviesEntity::class,MovieTrackingEntity::class],
    version = 1,
    exportSchema = false,


)
abstract class AppDatabase : RoomDatabase(){

    abstract val movieTrackingDao: MovieTrackingDao

    abstract val favoriteActorDao: FavoriteActorsDao

    abstract val favoriteMoviesDao: FavoriteMoviesDao
}
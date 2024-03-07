package com.example.actors.di

import com.example.actors.data.datasource.database.AppDatabase
import com.example.actors.data.datasource.database.dao.FavoriteActorsDao
import com.example.actors.data.datasource.database.dao.FavoriteMoviesDao
import com.example.actors.data.datasource.database.dao.MovieTrackingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesFavoriteActorsDao(
        database:AppDatabase
    ):FavoriteActorsDao{
        return database.favoriteActorDao
    }
    @Provides
    fun providesFavoriteMoviesDao(
        database: AppDatabase
    ) : FavoriteMoviesDao{
        return database.favoriteMoviesDao
    }
    @Provides
    fun providesMovieTrackingDao(
        database: AppDatabase
    ) : MovieTrackingDao{
        return database.movieTrackingDao
    }
}
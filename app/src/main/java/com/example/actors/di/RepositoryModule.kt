package com.example.actors.di

import com.example.actors.data.repository.actor.ActorRepositoryImpl
import com.example.actors.data.repository.movie.MovieRepositoryImpl
import com.example.actors.data.repository.movie.MovieTrailerRepositoryImpl
import com.example.actors.data.repository.search.SearchRepositoryImpl
import com.example.actors.domain.repository.actor.ActorRepository
import com.example.actors.domain.repository.movies.MovieRepository
import com.example.actors.domain.repository.movies.MovieTrailerRepository
import com.example.actors.domain.repository.search.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindActorRepository(impl: ActorRepositoryImpl): ActorRepository

    @Binds
    abstract fun bindMoviesRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindMovieTrailerRepository(impl: MovieTrailerRepositoryImpl): MovieTrailerRepository


}
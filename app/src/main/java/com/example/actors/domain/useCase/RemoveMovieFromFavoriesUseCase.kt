package com.example.actors.domain.useCase

import com.example.actors.data.model.FavoriteMovie
import com.example.actors.data.model.Movie
import com.example.actors.domain.repository.actor.ActorRepository
import com.example.actors.domain.repository.movies.MovieRepository
import javax.inject.Inject

class RemoveMoviesFromFavoritesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movie: FavoriteMovie){
        movieRepository.deleteSelectedFavoriteMovie(movie)
    }
}
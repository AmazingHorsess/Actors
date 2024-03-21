package com.example.actors.ui.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.FavoriteMovie
import com.example.actors.domain.repository.actor.ActorRepository
import com.example.actors.domain.repository.movies.MovieRepository
import com.example.actors.domain.useCase.RemoveActorsFromFavoritesUseCase
import com.example.actors.domain.useCase.RemoveMoviesFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val actorRepository: ActorRepository,
    private val removeMovieFromFavoritesUseCase: RemoveMoviesFromFavoritesUseCase,
    private val removeActorsFromFavoritesUseCase: RemoveActorsFromFavoritesUseCase
): ViewModel(){
    val favoriteMovies: Flow<List<FavoriteMovie>> = movieRepository.getAllFavoriteMovies()
    val favoriteActors: Flow<List<FavoriteActor>> = actorRepository.getAllFavoriteActors()

    fun removeMovieFromFavorites(movie: FavoriteMovie){
        viewModelScope.launch {
            removeMovieFromFavoritesUseCase.invoke(movie)
        }
    }
    fun removeActorFromFavorites(actor: FavoriteActor){
        viewModelScope.launch {
            removeActorsFromFavoritesUseCase
        }
    }
}
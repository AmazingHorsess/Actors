package com.example.actors.ui.screens.movieDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.toFavoriteMovie
import com.example.actors.domain.repository.actor.ActorRepository
import com.example.actors.domain.repository.movies.MovieRepository
import com.example.actors.domain.repository.movies.MovieTrailerRepository
import com.example.actors.domain.useCase.RemoveMoviesFromFavoritesUseCase
import com.example.actors.ui.navigation.AppDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val actorRepository: ActorRepository,
    private val movieTrailerRepository: MovieTrailerRepository,
    private val removeMovieFromFavoriteUseCase: RemoveMoviesFromFavoritesUseCase,
    savedStateHandle: SavedStateHandle,


): ViewModel(){
    private val movieId: Int = checkNotNull(savedStateHandle[AppDestinations.MOVIE_DETAILS_ID_KEY])

    var uiState by mutableStateOf(MovieDetailsUIState(movieData = null))
        private set

    var actorSheetUIState by mutableStateOf(ActorsSheetUIState(selectedActorDetails = null))
        private set

    var movieSheetUIState by mutableStateOf(MovieSheetUIState(selectedMovieDetails = null))
        private set

    val isFavoriteMovie: LiveData<Int> = movieRepository.isFavoriteMovie(movieId)

    init {
        viewModelScope.launch {
            try {
                startFetchingDetails()
                fetchMovieTrailers()
            } catch (e: IOException){
                Log.e("MyClass", "An IOException occurred: ${e.message}", e)

            }
        }
    }
    private suspend fun startFetchingDetails() {
        uiState = MovieDetailsUIState(isFetchingDetails = true, movieData = null)

        uiState = MovieDetailsUIState(
            movieData = movieRepository.getSelectedMovieData(movieId),
            similarMovies = movieRepository.getSimilarMoviesByIdData(movieId),
            recommendedMovies = movieRepository.getRecommendedMoviesByIdData(movieId),
            movieCast = movieRepository.getMovieCastByIdData(movieId),
            movieProviders = movieRepository.getMovieProvidersData(movieId),
            isFetchingDetails = false,
        )

    }
    private suspend fun fetchMovieTrailers() {
        try {
            val trailers = movieRepository.getMovieTrailer(movieId)
            // Выбираем первый трейлер из списка (если он есть) и обновляем UI
            val selectedTrailer = trailers.firstOrNull()
            uiState = uiState.copy(selectedTrailer = selectedTrailer)
        } catch (e: IOException) {
            Log.e("MyClass", "An IOException occurred while fetching trailers: ${e.message}", e)
        }
    }
    fun getSelectedMovieDetails(
        movieId: Int?
    ) {
        viewModelScope.launch {
            try {
                movieId?.let { id ->
                    val movieData = movieRepository.getSelectedMovieData(id)
                    movieSheetUIState = MovieSheetUIState(selectedMovieDetails =  movieData)
                }
            } catch (e: IOException) {
                Log.e("MyClass", "An IOException occurred: ${e.message}", e)


            }
        }
    }
    fun getSelectedActorDetails(
        actorId: Int?
    ) {
        viewModelScope.launch {
            try {
                actorId?.let { id ->
                    val actorData = actorRepository.getSelectedActorData(id)
                   actorSheetUIState = ActorsSheetUIState(selectedActorDetails = actorData)
                }
            } catch (e: IOException) {
                Log.e("MyClass", "An IOException occurred: ${e.message}", e)


            }
        }
    }
    fun addMovieToFavorites() {
        viewModelScope.launch {
            val movie: MovieDetail? = uiState.movieData
            if (movie != null) {
                movieRepository.addMovieToFavorites(
                    movie.toFavoriteMovie()
                )
            }
        }
    }
    fun removeMovieFromFavorites(){
        viewModelScope.launch {
            val movie: MovieDetail? = uiState.movieData
            if (movie != null){
                removeMovieFromFavoriteUseCase(
                    movie.toFavoriteMovie()
                )

            }
        }
    }


}
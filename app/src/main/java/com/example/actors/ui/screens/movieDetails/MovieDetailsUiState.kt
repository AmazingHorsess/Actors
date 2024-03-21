package com.example.actors.ui.screens.movieDetails

import com.example.actors.data.datasource.network.retrofit.model.ActorDetailsResponse
import com.example.actors.data.datasource.network.retrofit.service.NetworkResult
import com.example.actors.data.model.Cast
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.Trailer
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

data class MovieDetailsUIState(
    val movieData: MovieDetail?,
    val similarMovies: List<Movie> = emptyList(),
    val recommendedMovies: List<Movie> = emptyList(),
    val movieCast: List<Cast> = emptyList(),
    val selectedTrailer: Trailer? = null,
    val isFetchingDetails: Boolean = false,
    val movieProviders: MovieProvider = MovieProvider(ArrayList()),
)

/**
 * Models the UI state for the SheetContentActorDetails modal sheet.
 */
data class ActorsSheetUIState(
    val selectedActorDetails: Flow<NetworkResult<Response<ActorDetailsResponse>>>? = null
)

data class MovieSheetUIState(
    val selectedMovieDetails: MovieDetail? = null
)
package com.example.actors.ui.screens.movieDetails

import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.Cast
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.Trailer

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
    val selectedActorDetails: ActorDetail? = null
)

data class MovieSheetUIState(
    val selectedMovieDetails: MovieDetail? = null
)
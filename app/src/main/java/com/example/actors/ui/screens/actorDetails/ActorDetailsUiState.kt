package com.example.actors.ui.screens.actorDetails

import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail

data class ActorDetailsUIState(
    val castList: List<Movie> = listOf(),
    val actorData: ActorDetail? = null,
    val isFetchingDetails: Boolean = false,
)

/**
 * Models the UI state for the SheetContentMovieDetails modal sheet.
 */
data class ActorDetailsSheetUIState(
    val selectedMovieDetails: MovieDetail? = null
)
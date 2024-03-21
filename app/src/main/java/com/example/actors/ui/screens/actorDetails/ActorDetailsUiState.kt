package com.example.actors.ui.screens.actorDetails

import com.example.actors.data.datasource.network.retrofit.model.ActorDetailsResponse
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ActorDetailsUIState(
    val castList: Flow<List<Movie>> = emptyFlow(),
    val actorData: ActorDetailsResponse? = null ,
    val isFetchingDetails: Boolean = false,
)

/**
 * Models the UI state for the SheetContentMovieDetails modal sheet.
 */
data class ActorDetailsSheetUIState(
    val selectedMovieDetails: MovieDetail? = null
)
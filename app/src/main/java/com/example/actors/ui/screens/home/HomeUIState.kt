package com.example.actors.ui.screens.home

import  androidx.paging.PagingData
import com.example.actors.data.model.Actor
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.Trailer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUIState(
    var popularActorList:List<Actor> = emptyList(),
    var trendingActorList:List<Actor> = emptyList(),
    val isFetchingActors: Boolean = false,
    var upcomingMoviesList: List<Movie> = emptyList(),
    var nowPlayingMoviesList: Flow<PagingData<Movie>> = emptyFlow(),

    )

data class HomeSheetUIState(
    var selectedMovieDetails: MovieDetail? = null
)

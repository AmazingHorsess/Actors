package com.example.actors.ui.screens.home

import  androidx.paging.PagingData
import com.example.actors.data.datasource.network.retrofit.model.PopularActorResponse
import com.example.actors.data.datasource.network.retrofit.model.TrendingActorResponse
import com.example.actors.data.datasource.network.retrofit.service.NetworkResult
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUIState(
    var popularActorList: Flow<NetworkResult<PopularActorResponse>> = emptyFlow(),
    var trendingActorList: Flow<NetworkResult<TrendingActorResponse>> = emptyFlow(),
    val isFetchingActors: Boolean = false,
    var upcomingMoviesList: List<Movie> = emptyList(),
    var nowPlayingMoviesList: Flow<PagingData<Movie>> = emptyFlow(),

    )

data class HomeSheetUIState(
    var selectedMovieDetails: MovieDetail? = null
)

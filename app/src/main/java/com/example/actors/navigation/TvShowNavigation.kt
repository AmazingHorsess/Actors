package com.example.actors.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val  TV_SHOWS_ROUTE = "tvshows_route"

fun NavController.navigateToTvShows(navOptions: NavOptions) = navigate(TV_SHOWS_ROUTE,navOptions)

fun NavGraphBuilder.tvShowsScreen(onActorClick: (Int) -> Unit){
    composable(
        route = TV_SHOWS_ROUTE,

        ){
        TestTvShowsRoute(onActorClick)
    }
}

@Composable
private fun TestTvShowsRoute(
    onActorClick: (Int) -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Actors route")

    }
}
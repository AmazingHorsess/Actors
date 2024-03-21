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

const val MOVIES_ROUTE = "movies_route"

fun NavController.navigateToMovies(navOptions: NavOptions) = navigate(MOVIES_ROUTE,navOptions)

fun NavGraphBuilder.moviesScreen(onActorClick: (Int) -> Unit){
    composable(
        route = MOVIES_ROUTE,

        ){
        TestMovieRoute(onActorClick)
    }
}

@Composable
private fun TestMovieRoute(
    onActorClick: (Int) -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Actors route")

    }
}
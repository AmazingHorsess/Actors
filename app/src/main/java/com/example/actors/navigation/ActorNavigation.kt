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

const val ACTORS_ROUTE = "actors_route"

fun NavController.navigateToActors(navOptions: NavOptions) = navigate(ACTORS_ROUTE,navOptions)

fun NavGraphBuilder.actorsScreen(onActorClick: (Int) -> Unit){
    composable(
        route = ACTORS_ROUTE,

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
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

const val MY_LIST_ROUTE = "mylist_route"

fun NavController.navigateToMyList(navOptions: NavOptions) = navigate(MY_LIST_ROUTE,navOptions)

fun NavGraphBuilder.myListScreen(onActorClick: (Int) -> Unit){
    composable(
        route = MY_LIST_ROUTE,

        ){
        TestMyListRoute(onActorClick)
    }
}

@Composable
private fun TestMyListRoute(
    onActorClick: (Int) -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Actors route")

    }
}
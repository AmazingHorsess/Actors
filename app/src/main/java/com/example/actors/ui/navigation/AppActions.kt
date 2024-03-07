package com.example.actors.ui.navigation

import android.util.Log
import androidx.navigation.NavHostController
import com.example.actors.ui.screens.search.SearchType

/**
 *All user actions for navigationg
 * @property navContoller helps us navigate by performing action.
 * @property routes destionations to navigate once action is tgirred.
 */
class AppActions(
    private val navContoller: NavHostController,
    private val routes: AppDestinations
) {
    // Triggered when user tries to navigate to details of an actor from list with id.
    val navigateToSelectedActor:(Int) -> Unit = {
        actorId:Int -> navContoller.navigate("${routes.ACTOR_DETAIL_ROUTE}/$actorId")}

    // Triggered when user tries to navigate to details of an movie from list with id.
    val navigateToSelectedMovie:(Int) -> Unit = {
        movieId:Int -> navContoller.navigate("${routes.MOVIE_DETAILS_ROUTE}/$movieId")
    }

    // Navigates to SearchScreen.
    val navigateToSearch:(SearchType) -> Unit = {
        searchType: SearchType ->  navContoller.navigate("${routes.SEARCH_ROUTE}/$searchType")
    }

    // Triggered when user tries to navigate to favorite screen from More menu.
    val navigateToFavorite: () -> Unit = {
        navContoller.navigate(routes.FAVORITE_ROUTE)
    }

    // Triggered when user tries to navigate to about screen from home options menu.
    val navigateToAbout: () -> Unit = {
        navContoller.navigate(routes.ABOUT_ROUTE)
    }

    // Navigates to previous screen from current screen.
    val navigateUp:() -> Unit = {
        navContoller.navigate("${routes.HOME_ROUTE}")
        Log.e("Test", "nazhal")
    }

}
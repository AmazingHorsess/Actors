package com.example.actors.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.actors.ui.screens.actorDetails.ActorDetailsScreen
import com.example.actors.ui.screens.favorite.FavoriteScreenUI
import com.example.actors.ui.screens.favorite.FavoritesScreen
import com.example.actors.ui.screens.home.HomeScreen
import com.example.actors.ui.screens.movieDetails.MovieDetailScreen
import com.example.actors.ui.screens.search.SearchScreen
import com.example.actors.ui.screens.search.SearchType


@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.HOME_ROUTE,
    routes: AppDestinations = AppDestinations,
) {
    val navController = rememberNavController()

    val actions = remember(navController) {
        AppActions(navController, routes)
    }


    NavHost(
        navController = navController,
        startDestination = startDestination ,){
        composable(
            AppDestinations.HOME_ROUTE
        ){
            HomeScreen(
                navigateToSelectedActor = actions.navigateToSelectedActor,
                navigateToSearch = actions.navigateToSearch,
                navigateToFavorite = actions.navigateToFavorite,
                navigateToSelectedMovie = actions.navigateToSelectedMovie,
                navigateToAbout = actions.navigateToAbout,


            )


        }
        composable(
            route = "${routes.ACTOR_DETAIL_ROUTE}/{${routes.ACTOR_DETAIL_ID_KEY}}",
            arguments = listOf(
                navArgument(routes.ACTOR_DETAIL_ID_KEY) { type = NavType.IntType }
            )
        ){
            ActorDetailsScreen(
                navigateToSelectedMovie = actions.navigateToSelectedMovie,
                navigateUp = actions.navigateUp
                )
                


        }
        composable(
            route= "${routes.FAVORITE_ROUTE}",
        ){
            FavoritesScreen(
                navigateUp = actions.navigateUp,
                navigateToSelectedMovie = actions.navigateToSelectedMovie,
                navigateToSelectedActor = actions.navigateToSelectedActor ,

            )

        }
        composable(
            route = "${AppDestinations.SEARCH_ROUTE}/{${routes.SEARCH_TYPE}}",
            arguments = listOf(
                navArgument(routes.SEARCH_TYPE) { type = NavType.EnumType(SearchType::class.java) }
            )
        ) {
            SearchScreen(
                navigateUp = actions.navigateUp,
                navigateToSelectedActor = actions.navigateToSelectedActor,
                navigateToSelectedMovie = actions.navigateToSelectedMovie,
            )
        }
        composable(
            route ="${routes.MOVIE_DETAILS_ROUTE}/{${routes.MOVIE_DETAILS_ID_KEY}}",
            arguments = listOf(
                navArgument(routes.MOVIE_DETAILS_ID_KEY) { type = NavType.IntType }
            )
        ){
           MovieDetailScreen(
                navigateUp = actions.navigateUp,
                navigateToSelectedMovie = actions.navigateToSelectedMovie,
               navigateToSelectedActor = actions.navigateToSelectedActor
            )

        }

    }
}
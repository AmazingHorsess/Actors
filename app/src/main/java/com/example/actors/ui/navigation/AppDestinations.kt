package com.example.actors.ui.navigation



/**
 * All app routes
 */
object AppDestinations {
    //defalut destination
    const val HOME_ROUTE = "home"

    const val  FAVORITE_ROUTE = "favorites"

    //destination for searching actors
    const val SEARCH_ROUTE = "search"
    //search based on type
    const val SEARCH_TYPE = "searchType"

    const val ABOUT_ROUTE = "about"

    //list to details destionations
    const val ACTOR_DETAIL_ROUTE = "actor-detail"
    //list to selected actor detail destination
    const val ACTOR_DETAIL_ID_KEY = "actorId"

    // destination for searching actors
    const val MOVIE_DETAILS_ROUTE = "movie-detail"
    // list to selected actor detail destination
    const val MOVIE_DETAILS_ID_KEY = "movieId"
}
package com.example.actors.ui.screens.search

import com.example.actors.data.model.Actor
import com.example.actors.data.model.Movie

/**
 * UI state for the [SearchScreen] screen.
 */
sealed class SearchUIState {

    data class MovieSearch(
        val movieList: List<Movie> = listOf(),
        val isSearchingResults: Boolean = false,
    ): SearchUIState()

    data class ActorSearch(
        val actorList: List<Actor> = listOf(),
        val isSearchingResults: Boolean = false,
    ): SearchUIState()
}
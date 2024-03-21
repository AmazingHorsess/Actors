package com.example.actors.ui.screens.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.FavoriteMovie

@Composable
fun FavoritesScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToSelectedMovie: (Int) -> Unit,
    navigateToSelectedActor: (Int) -> Unit,
) {
    val favoriteMovies by favoriteViewModel.favoriteMovies.collectAsState(emptyList())
    val favoriteActors by favoriteViewModel.favoriteActors.collectAsState(emptyList())

    val removeFavoriteMovie = { favoriteMovie: FavoriteMovie ->
        favoriteViewModel.removeMovieFromFavorites(favoriteMovie)
    }

    val removeFavoriteActor = { favoriteActor: FavoriteActor ->
        favoriteViewModel.removeActorFromFavorites(favoriteActor)
    }

    FavoriteScreenUI(
        navigateUp = navigateUp,
        favoriteMovies = favoriteMovies,
        navigateToSelectedMovie = navigateToSelectedMovie,
        removeFavoriteMovie = removeFavoriteMovie,
        navigateToSelectedActor = navigateToSelectedActor,
        favoriteActors = favoriteActors,
        removeFavoriteActor = removeFavoriteActor
    )
}
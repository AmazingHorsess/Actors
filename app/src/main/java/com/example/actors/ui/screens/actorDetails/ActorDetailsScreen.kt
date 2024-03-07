package com.example.actors.ui.screens.actorDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun ActorDetailsScreen(
    viewModel: ActorDetailsViewModel = hiltViewModel(),
    navigateToSelectedMovie: (Int) -> Unit,
    navigateUp: () -> Unit,
) {
    val detailUIState = viewModel.detailUIState
    val sheetUIState = viewModel.sheetUIState
    val movieId by viewModel.isFavoriteMovie.observeAsState()

    ActorDetailsUi(
        detailUiState = detailUIState,
        sheetUIState = sheetUIState,
        navigateToSelectedMovie = navigateToSelectedMovie,
        isFavoriteMovie = movieId != 0 && movieId != null,
        navigateUp = navigateUp,
        getSelectedMovieDetails = { viewModel.getSelectedMovieDetails(it) },
        addActorToFavorites = { viewModel.addActorToFavorites() },
        removeActorFromFavorites = { viewModel.removeActorFromFavorites() }
    )
}
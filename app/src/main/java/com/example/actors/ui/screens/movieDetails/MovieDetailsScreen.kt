package com.example.actors.ui.screens.movieDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.actors.data.model.BottomSheetType

/**
 * Screen shows details for the selected movie.
 * This destination can be accessed from [HomeScreen] & [ActorDetailsScreen].
 */
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navigateToSelectedMovie: (Int) -> Unit,
    navigateToSelectedActor: (Int) -> Unit,
    navigateUp: () -> Unit
) {
    val uiState = viewModel.uiState
    val actorsSheetUIState = viewModel.actorSheetUIState
    val movieSheetUIState = viewModel.movieSheetUIState

    val movieId by viewModel.isFavoriteMovie.observeAsState()

    val selectedBottomSheet = remember {
        mutableStateOf<BottomSheetType?>(BottomSheetType.MovieDetailBottomSheet)
    }

    val selectBottomSheetCallback = setBottomSheetCallBack(viewModel, selectedBottomSheet)

    MovieDetailsUi(
        uiState = uiState,
        actorsSheetUIState = actorsSheetUIState,
        movieSheetUIState = movieSheetUIState,
        navigateUp = navigateUp,
        selectedBottomSheet = selectedBottomSheet,
        selectBottomSheetCallback = selectBottomSheetCallback,
        isFavoriteMovie = movieId != 0 && movieId != null,
        addMovieToFavorites = { viewModel.addMovieToFavorites() },
        removeMovieFromFavorites = { viewModel.removeMovieFromFavorites() },
        navigateToSelectedMovie = navigateToSelectedMovie,
        navigateToSelectedActor = navigateToSelectedActor
    )
}

private fun setBottomSheetCallBack(
    viewModel: MovieDetailsViewModel,
    selectedBottomSheet: MutableState<BottomSheetType?>
) = { bottomSheetType: BottomSheetType ->
    when (bottomSheetType) {
        BottomSheetType.MovieDetailBottomSheet -> {
            viewModel.getSelectedMovieDetails(bottomSheetType.movieOrActorId)
        }
        BottomSheetType.ActorDetailBottomSheet -> {
            viewModel.getSelectedActorDetails(bottomSheetType.movieOrActorId)
        }
    }
    selectedBottomSheet.value = bottomSheetType
}
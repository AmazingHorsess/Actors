package com.example.actors.ui.screens.actorDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.actors.ui.components.ImageBackgroundThemeGenerator
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetLayout
import com.example.actors.ui.components.ShowProgressIndicator
import com.example.actors.ui.screens.actorDetails.composables.ActorBackgroundWithGradientForeground
import com.example.actors.ui.modalSheets.SheetContentMovieDetails
import com.example.actors.ui.modalSheets.manageBottomSheetState
import com.example.actors.ui.modalSheets.modalBottomSheetState
import com.example.actors.ui.screens.movieDetails.composables.FloatingAddToFavoritesButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ActorDetailsUi(
    detailUiState: ActorDetailsUIState,
    sheetUIState: ActorDetailsSheetUIState,
    navigateToSelectedMovie: (Int) -> Unit,
    isFavoriteMovie:Boolean,
    navigateUp: () -> Unit,
    getSelectedMovieDetails : (Int) -> Unit,
    addActorToFavorites: () -> Unit,
    removeActorFromFavorites :() -> Unit

) {
    val showFab = rememberSaveable { mutableStateOf(true) }
    val actorProfileUrl = "${detailUiState.actorData?.profilePath}"
    val modalSheetState = modalBottomSheetState()
    val openActorDetailsBottomSheet = manageBottomSheetState(modalSheetState = modalSheetState)
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        scrimColor = Color.Black.copy(alpha = 0.5f),
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetContent = {
            SheetContentMovieDetails(
                movie = sheetUIState.selectedMovieDetails,
                navigateToSelectedMovie = navigateToSelectedMovie
            )

        }
    ) {
        ImageBackgroundThemeGenerator(
            imageUrl = actorProfileUrl
        ) {
            Box {
                // Draws gradient from image and overlays on it.
                ActorBackgroundWithGradientForeground(imageUrl = actorProfileUrl)

                Column {
                    ActorDetailsTopAppBar(
                        navigateUp = navigateUp,
                        title = "${detailUiState.actorData?.name}"
                    )
                    // Custom top app bar


                    // Main details content
                    ActorDetailsContent(
                        navigateUp = navigateUp,
                        detailUiState = detailUiState,
                        openActorDetailsBottomSheet = openActorDetailsBottomSheet,
                        getSelectedMovieDetail = getSelectedMovieDetails,
                        showFab = showFab
                    )
                }
                // Progress bar
                ShowProgressIndicator(isLoadingData = detailUiState.isFetchingDetails)
            }
        }

        if (showFab.value) {
            FloatingAddToFavoritesButton(
                isFavorite = isFavoriteMovie,
                addToFavorites = addActorToFavorites,
                removeFromFavorites = removeActorFromFavorites
            )
        }
    }
}


        




//@Preview(showBackground = true, backgroundColor = 0xFF211a18)
//@Composable
//private fun ActorDetailsUIDarkPreview() {
//    ActorsTheme(darkTheme = true) {
//        ActorDetailsUi(
//            detailUiState = ActorDetailsUIState(
//                castList = listOf(Movie(1,"test","test","test")),
//                actorData = fakeActorDetail,
//                isFetchingDetails = false
//            ),
//            sheetUIState = ActorDetailsSheetUIState(fakeMovieDetail),
//            navigateToSelectedMovie = {},
//            isFavoriteMovie = true,
//            navigateUp = {},
//            getSelectedMovieDetails = {},
//            addActorToFavorites = {},
//            removeActorFromFavorites = {}
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun ActorDetailsUILightPreview() {
//    ActorsTheme(darkTheme = false) {
//        ActorDetailsUi(
//            detailUiState = ActorDetailsUIState(
//                castList = listOf(Movie(1,"test","test","test")),
//                actorData = fakeActorDetail,
//                isFetchingDetails = false
//            ),
//            sheetUIState = ActorDetailsSheetUIState(fakeMovieDetail),
//            navigateToSelectedMovie = {},
//            isFavoriteMovie = true,
//            navigateUp = {},
//            getSelectedMovieDetails = {},
//            addActorToFavorites = {},
//            removeActorFromFavorites = {}
//        )
//    }
//}









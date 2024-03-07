package com.example.actors.ui.screens.movieDetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.actors.data.datasource.fake.fakeMovieCastList
import com.example.actors.data.datasource.fake.fakeMovieDetail
import com.example.actors.data.datasource.fake.fakeMovieList
import com.example.actors.data.model.BottomSheetType
import com.example.actors.data.model.Flatrate
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.Trailer
import com.example.actors.ui.animations.LayerRevealImage
import com.example.actors.ui.components.ErrorTrailerLoadingShowSnackbar
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetLayout
import com.example.actors.ui.modalSheets.SheetContentActorDetail
import com.example.actors.ui.modalSheets.SheetContentMovieDetails
import com.example.actors.ui.modalSheets.SheetContentMovieProviders
import com.example.actors.ui.modalSheets.manageBottomSheetState
import com.example.actors.ui.modalSheets.modalBottomSheetState
import com.example.actors.ui.theme.ActorsTheme
import kotlinx.coroutines.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsUi(
    modifier: Modifier = Modifier,
    uiState: MovieDetailsUIState,
    actorsSheetUIState: ActorsSheetUIState,
    movieSheetUIState: MovieSheetUIState,
    navigateUp: () -> Unit,
    selectedBottomSheet: MutableState<BottomSheetType?>,
    selectBottomSheetCallback: (BottomSheetType) -> Unit,
    isFavoriteMovie: Boolean,
    addMovieToFavorites: () -> Unit,
    removeMovieFromFavorites: () -> Unit,
    navigateToSelectedMovie: (movieId: Int) -> Unit,
    navigateToSelectedActor: (actorId:Int) -> Unit

){
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    val modalSheetState = modalBottomSheetState(
        animationSpec = tween(durationMillis = 300, delayMillis = 50)
    )
    val openMovieDetailsBottomSheet = manageBottomSheetState(
        modalSheetState = modalSheetState
    )
    // This helps us reveal screen content with fadeIn anim once reveal effect is completed.
    val isLayerRevealAnimationEnded = rememberSaveable { mutableStateOf(false) }
    // Change button state with respect to scroll changes.
    val showFab = rememberSaveable { mutableStateOf(true) }
    // Remember scroll state to change button state.
    val showBottomSheetScaffold = rememberSaveable { mutableStateOf(!isLayerRevealAnimationEnded.value) }
    val bottomSheetPeakValue = when{
        showBottomSheetScaffold.value ->72.dp
        else -> 0.dp
    }
    val animatedScaffoldSheetPeekHeight = getAnimatedSheetPeekHeight(bottomSheetPeakValue)
    ModalBottomSheetLayout(
        scrimColor = Color.Black.copy(alpha = 0.5f),
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetState =modalSheetState,

        sheetContent = {
            GetBottomSheetContent(
                selectedBottomSheet.value,
                actorsSheetUIState,
                movieSheetUIState,
                navigateToSelectedMovie,
                navigateToSelectedActor,

            )

        },
    ) {
        val scaffoldState = rememberBottomSheetScaffoldState()
        BottomSheetScaffold(
            modifier = Modifier.systemBarsPadding(),
            scaffoldState = scaffoldState,
            sheetPeekHeight = animatedScaffoldSheetPeekHeight,
            sheetContent = {
                SheetContentMovieProviders(
                    movieProvider = uiState.movieProviders,
                    isFavoriteMovie = isFavoriteMovie,
                    addMovieToFavorites = addMovieToFavorites,
                    removeMovieFromFavorites = removeMovieFromFavorites,
                )
            }
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("TestTag:MovieDetailScreen")
            ) {
                MovieDetailsUiContent(
                    uiState = uiState,
                    isLayerRevealAnimationEnded = isLayerRevealAnimationEnded,
                    state = state,
                    modifier = modifier,
                    navigateUp = navigateUp,
                    selectBottomSheetCallback = selectBottomSheetCallback,
                    openMovieDetailsBottomSheet = openMovieDetailsBottomSheet,
                    showFab = showFab,
                    showBottomSheetScaffold = showBottomSheetScaffold,
                    snackbarHostState = scaffoldState.snackbarHostState
                )
            }
            ErrorTrailerLoadingShowSnackbar(snackbarHostState = scaffoldState.snackbarHostState)

        }

    }
}
@Composable
private fun getAnimatedSheetPeekHeight(bottomSheetPeakValue: Dp): Dp {
    val transition = updateTransition(targetState = bottomSheetPeakValue, label = "")
    val animatedSheetPeekHeight by transition.animateDp(
        transitionSpec = {
            spring(stiffness = Spring.StiffnessLow)
        }, label = ""
    ) { value -> value }
    return animatedSheetPeekHeight
}
@Composable
fun MovieDetailsUiContent(
    uiState: MovieDetailsUIState,
    isLayerRevealAnimationEnded: MutableState<Boolean>,
    state: MutableTransitionState<Boolean>,
    modifier: Modifier,
    navigateUp: () -> Unit,
    selectBottomSheetCallback: (BottomSheetType) -> Unit,
    openMovieDetailsBottomSheet: () -> Job,
    showFab: MutableState<Boolean>,
    showBottomSheetScaffold: MutableState<Boolean>,
    snackbarHostState: SnackbarHostState
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    // Background poster with layer reveal effect
    LayerRevealImage(uiState.movieData?.poster, isLayerRevealAnimationEnded)
    // Fade enter animation detail screen once layer reveal completes
    if (isLayerRevealAnimationEnded.value) {
        AnimatedVisibility(
            visibleState = state,
            enter = fadeIn()
        ) {
            // Main details content
            MovieDetailsContent(
                modifier = modifier,
                uiState = uiState,
                navigateUp = navigateUp,
                showFab = showFab,
                openMovieDetailsBottomSheet = openMovieDetailsBottomSheet,
                selectBottomSheetCallback = selectBottomSheetCallback,
                showBottomSheetScaffold = showBottomSheetScaffold,
                snackbarHostState = snackbarHostState,
                lifecycleOwner = lifecycleOwner

            )
        }
    }
}
@Composable
private fun GetBottomSheetContent(
    bottomSheetType: BottomSheetType?,
    sheetUiState: ActorsSheetUIState,
    movieSheetUIState: MovieSheetUIState,
    navigateToSelectedMovie: (Int) -> Unit,
    navigateToSelectedActor: (Int) -> Unit
) {
    bottomSheetType?.let { type ->
        when (type) {
            BottomSheetType.MovieDetailBottomSheet -> {
                SheetContentMovieDetails(
                    movie = movieSheetUIState.selectedMovieDetails,
                    navigateToSelectedMovie = navigateToSelectedMovie
                )
            }
            BottomSheetType.ActorDetailBottomSheet -> {
                SheetContentActorDetail(
                    actor = sheetUiState.selectedActorDetails,
                    navigateToSelectedActor = navigateToSelectedActor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieDetailsUILightPreview() {
    ActorsTheme(darkTheme = false) {
        val bottomSheetTypeState = remember {
            mutableStateOf<BottomSheetType?>(BottomSheetType.MovieDetailBottomSheet)
        }
        MovieDetailsUi(
            uiState = MovieDetailsUIState(
                movieData = fakeMovieDetail,
                similarMovies = fakeMovieList(),
                recommendedMovies = fakeMovieList(),
                movieCast = fakeMovieCastList(),
                isFetchingDetails = false,
                movieProviders = MovieProvider(
                    arrayListOf(Flatrate("", 1, "test"))
                ),


            ),
            actorsSheetUIState = ActorsSheetUIState(
                null
            ) ,
            movieSheetUIState = MovieSheetUIState(
                null
            ),
            navigateUp = { /*TODO*/ },
            selectedBottomSheet = bottomSheetTypeState,
            selectBottomSheetCallback = {} ,
            isFavoriteMovie = true,
            addMovieToFavorites = { /*TODO*/ },
            removeMovieFromFavorites = { /*TODO*/ },
            navigateToSelectedMovie = {},
            navigateToSelectedActor = {}
        )

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun MovieDetailsUIDarkPreview() {
    ActorsTheme(darkTheme = true) {
        MovieDetailsContent(
            uiState = MovieDetailsUIState(
                movieData = fakeMovieDetail,
                similarMovies = fakeMovieList(),
                recommendedMovies = fakeMovieList(),
                movieCast = fakeMovieCastList(),
                isFetchingDetails = false,
                movieProviders = MovieProvider(
                    arrayListOf(Flatrate("", 1, "test"))
                ),

            ),
            navigateUp = {},
            showFab = remember { mutableStateOf(true) },
            openMovieDetailsBottomSheet = { Job() },
            selectBottomSheetCallback = {},
            showBottomSheetScaffold = remember { mutableStateOf(true) },
            snackbarHostState = SnackbarHostState(),
            lifecycleOwner = LocalLifecycleOwner.current
        )
    }
}

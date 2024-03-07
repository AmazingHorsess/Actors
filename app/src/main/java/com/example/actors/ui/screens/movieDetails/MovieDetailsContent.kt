package com.example.actors.ui.screens.movieDetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.actors.data.datasource.fake.fakeMovieCastList
import com.example.actors.data.datasource.fake.fakeMovieDetail
import com.example.actors.data.datasource.fake.fakeMovieList
import com.example.actors.data.model.BottomSheetType
import com.example.actors.data.model.Flatrate
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.Trailer
import com.example.actors.ui.components.CategoryTitle
import com.example.actors.ui.screens.movieDetails.composables.GetMovieCast
import com.example.actors.ui.screens.movieDetails.composables.GetSimilarOrRecommendedMovies
import com.example.actors.ui.screens.movieDetails.composables.MovieDetailImageBanner
import com.example.actors.ui.screens.movieDetails.composables.MovieDetailOverviewText
import com.example.actors.ui.screens.movieDetails.composables.MovieGenre
import com.example.actors.ui.screens.movieDetails.composables.MovieTrailerVideoPlayer

import com.example.actors.ui.theme.ActorsTheme
import kotlinx.coroutines.Job

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsContent(
    modifier: Modifier = Modifier,
    uiState: MovieDetailsUIState,
    navigateUp: () -> Unit,
    showFab: MutableState<Boolean>,
    openMovieDetailsBottomSheet: () -> Job,
    selectBottomSheetCallback: (BottomSheetType) -> Unit,
    showBottomSheetScaffold: MutableState<Boolean>,
    snackbarHostState: SnackbarHostState,
    lifecycleOwner: LifecycleOwner
    ){
    val movieData = uiState.movieData
    val trailer = uiState.selectedTrailer

    val listState = rememberLazyListState()

    val showTopBarBackground = remember {
        derivedStateOf { listState
            .firstVisibleItemScrollOffset > 0
        }
    }

    LaunchedEffect(showTopBarBackground.value){
        showBottomSheetScaffold.value = ! showTopBarBackground.value
    }
    showFab.value = !listState.isScrollInProgress

    LazyColumn(
        state = listState,
        modifier = modifier.navigationBarsPadding()

    ){
        stickyHeader {
            MovieDetailTopAppBar(
                modifier = modifier.testTag("TestTag:MovieDetailTopAppBar"),
                navigateUp = navigateUp,
                title = movieData?.movieTitle,
                showTopBarBackground = showTopBarBackground
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            MovieGenre(movieData?.genres)
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth().height(200.dp)
            ){
                item{
                    MovieTrailerVideoPlayer(videoUrl = trailer?.key,snackbarHostState,lifecycleOwner)
                    MovieDetailImageBanner(movieData?.banner)

                }
            }



            Spacer(modifier = Modifier.height(16.dp))
            MovieDetailOverviewText(movieData?.overview)
            Spacer(modifier = Modifier.height(16.dp))
            CategoryTitle(title = "Cast", alpha = 1f)
            Spacer(modifier = Modifier.height(16.dp))
            GetMovieCast(
                uiState = uiState,
                openMovieDetailsBottomSheet = openMovieDetailsBottomSheet,
                selectBottomSheetCallback = selectBottomSheetCallback

            )
            Spacer(modifier = Modifier.height(12.dp))
            CategoryTitle(title = "Recommended", alpha = 1f)
            GetSimilarOrRecommendedMovies(
                movieList = uiState.recommendedMovies,
                openMovieDetailBottomSheet = openMovieDetailsBottomSheet,
                selectBottomSheetCallBack = selectBottomSheetCallback
            )
            Spacer(modifier = Modifier.height(52.dp))
        }

    }

}

@Composable
@Preview(showBackground = true)
private fun MovieDetailsContentLight(){
    val showFab = remember{ mutableStateOf(true) }
    val showBottomSheetScaffold = remember{ mutableStateOf(true) }
    ActorsTheme(darkTheme = false) {
        MovieDetailsContent(
            uiState = MovieDetailsUIState(
                movieData = fakeMovieDetail,
                similarMovies = fakeMovieList(),
                movieCast = fakeMovieCastList(),
                isFetchingDetails = false,
                movieProviders = MovieProvider(
                    flatrate = kotlin.collections.arrayListOf()
                ),
                selectedTrailer = Trailer("","","","","",5,"",true,"","")

            ),
            navigateUp = { },
            showFab =  showFab,
            openMovieDetailsBottomSheet = { Job() },
            selectBottomSheetCallback = {_: BottomSheetType ->} ,
            showBottomSheetScaffold = showBottomSheetScaffold,
            snackbarHostState = SnackbarHostState(),
            lifecycleOwner = LocalLifecycleOwner.current)


    }
}
@Composable
@Preview(showBackground = true)
private fun MovieDetailsContentBlack(){
    val showFab = remember{ mutableStateOf(true) }
    val showBottomSheetScaffold = remember{ mutableStateOf(true) }
    ActorsTheme(darkTheme = true) {
        MovieDetailsContent(
            uiState = MovieDetailsUIState(
                movieData = fakeMovieDetail,
                similarMovies = fakeMovieList(),
                movieCast = fakeMovieCastList(),
                isFetchingDetails = false,
                movieProviders = MovieProvider(
                    arrayListOf(Flatrate("", 1, ""))
                ),

            ),
            navigateUp = { },
            showFab =  showFab,
            openMovieDetailsBottomSheet = { Job() },
            selectBottomSheetCallback = {_: BottomSheetType ->} ,
            showBottomSheetScaffold = showBottomSheetScaffold,
            snackbarHostState = SnackbarHostState(),
            lifecycleOwner = LocalLifecycleOwner.current
        )


    }
}
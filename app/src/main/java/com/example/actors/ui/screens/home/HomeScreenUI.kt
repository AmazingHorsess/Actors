package com.example.actors.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.actors.ui.components.ApiKeyMissingShowSnackbar
import com.example.actors.ui.components.AppDivider
import com.example.actors.ui.components.IfOfflineShowSnackbar
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetLayout
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetValue
import com.example.actors.ui.components.TabItem
import com.example.actors.ui.components.TabsContainer
import com.example.actors.ui.modalSheets.OptionsModalSheetContent
import com.example.actors.ui.screens.home.tabs.ActorsTabContent
import com.example.actors.ui.screens.home.tabs.MoviesTabContent
import com.example.actors.ui.screens.home.tabs.TvShowsTabContent

import com.example.actors.ui.screens.search.SearchType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    navigateToFavorite: () -> Unit,
    navigateToSearch: (SearchType) -> Unit,
    navigateToAbout: () -> Unit,
    navigateToSearchBySearchType: SearchType,
    navigateToSelectedActor: (Int) -> Unit,
    navigateToSelectedMovie: (Int) -> Unit,
    uiState: HomeUIState,
    sheetUiState: HomeSheetUIState,
    updateHomeSearchType: (SearchType) -> Unit
    ){
    val scope = rememberCoroutineScope()

    var isBottomSheetVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val snackbarHostState = remember { SnackbarHostState() }


    val modalSheetState = com.example.actors.ui.components.ModalBottomSheetLayout.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = tween(durationMillis = 500),

    )



    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.testTag("tag")
    ) {
        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            scrimColor = Color.Black.copy(alpha = 0.5f),
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetBackgroundColor = MaterialTheme.colorScheme.background,
            sheetContent = {
                OptionsModalSheetContent(
                    modalSheetSheet = modalSheetState,
                    navigateToFavorite = navigateToFavorite,
                    navigateToSearch = { navigateToSearch(SearchType.MOVIES) },
                    navigateToProfile = { },
                    navigateToAbout = navigateToAbout
                )
            }
        ) {
            Scaffold(

                topBar = {
                         HomeTopAppBar(navigateToSearch = navigateToSearch,
                             searchType = navigateToSearchBySearchType
                         )


                },
                bottomBar = {
                    HomeBottomBar(
                        modalSheetSheet = modalSheetState
                    )
                },
                snackbarHost = { HomeSnackbar(hostState = snackbarHostState) }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)){
                    HomeScreenUi(
                        navigateToSelectedActor = navigateToSelectedActor,
                        homeUiState = uiState,
                        homeSheetUIState = sheetUiState,
                        navigateToSelectedMovie = navigateToSelectedMovie,
                        updateToSearchType = updateHomeSearchType
                    )
                    // Perform network check and show snackbar if offline
                    IfOfflineShowSnackbar(snackbarHostState)

                    // If Api key is missing, show a SnackBar.
                    ApiKeyMissingShowSnackbar(snackbarHostState)

                }



            }


        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun  HomeScreenUi(
    navigateToSelectedActor: (Int) -> Unit,
    navigateToSelectedMovie: (Int) -> Unit,
    homeUiState: HomeUIState,
    homeSheetUIState: HomeSheetUIState,
    updateToSearchType: (navigateToSearchType:SearchType) -> Unit

){
    val popularActorsList = rememberLazyListState()
    val trendingActorsListState = rememberLazyListState()

    val homeTabs = listOf(
        TabItem("Actors"),
        TabItem("Movies"),
        TabItem("TV Shows")
    )
    val homePagerState = rememberPagerState(pageCount = {homeTabs.size})
    Column(

        Modifier.fillMaxSize()) {
        TabsContainer(tabs = homeTabs, pagerState = homePagerState)
        AppDivider(verticalPadding = 10.dp, thickness = 1.dp)

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Box(modifier = Modifier.weight(1f)){



            HorizontalPager(
                state = homePagerState,

                ) {
                when(it){
                    0 -> {
                        updateToSearchType(SearchType.ACTORS)
                        ActorsTabContent(
                            homeUIState = homeUiState,
                            navigateToSelectedActor = navigateToSelectedActor,
                            popularActorsListState = popularActorsList,
                            trendingActorsListState = trendingActorsListState
                        )

                    }

                    1 -> {
                        updateToSearchType(SearchType.MOVIES)
                        MoviesTabContent(
                            homeUIState = homeUiState,
                            navigateToSelectedMovie = navigateToSelectedMovie ,
                            homeSheetUIState = homeSheetUIState
                        )


                    }
                    2 -> {
                        TvShowsTabContent(homeSheetUIState = homeSheetUIState)

                    }
                }

            }

        }



    }

}
//@Preview(showBackground = true)
//@Composable
//private fun HomeScreenUILightPreview() {
//    ActorsTheme(darkTheme = false) {
//        HomeScreenUI(
//            navigateToSelectedActor = {},
//            navigateToSelectedMovie = {},
//            navigateToFavorite = {},
//            navigateToSearch = {},
//            navigateToSearchBySearchType = SearchType.ACTORS,
//            sheetUiState = HomeSheetUIState(fakeMovieDetail),
//            updateHomeSearchType = {} ,
//            navigateToAbout = {},
//            uiState = HomeUIState(
//                popularActorList = fakeActorsList(),
//                trendingActorList = fakeActorsList(),
//                upcomingMoviesList = fakeMovieList(),
//                nowPlayingMoviesList = flow { fakeMovieList() },
//                isFetchingActors = false,
//
//
//            )
//
//        )
//    }
//}
//@Preview(showBackground = true)
//@Composable
//private fun HomeScreenUiDarkPreview() {
//    ActorsTheme(darkTheme = true) {
//        HomeScreenUI(
//            navigateToSelectedActor = {},
//            navigateToSelectedMovie = {},
//            navigateToFavorite = {},
//            navigateToSearch = {},
//            navigateToSearchBySearchType = SearchType.ACTORS,
//            sheetUiState = HomeSheetUIState(fakeMovieDetail),
//            updateHomeSearchType = {} ,
//            navigateToAbout = {},
//            uiState = HomeUIState(
//                popularActorList = fakeActorsList(),
//                trendingActorList = fakeActorsList(),
//                upcomingMoviesList = fakeMovieList(),
//                nowPlayingMoviesList = flow { fakeMovieList() },
//                isFetchingActors = false,
//                ),
//        )
//    }
//}
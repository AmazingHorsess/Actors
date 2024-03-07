package com.example.actors.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.actors.ui.screens.search.SearchType

import androidx.compose.runtime.livedata.observeAsState
import com.example.actors.data.datasource.fake.fakeActorsList
import com.example.actors.data.datasource.fake.fakeMovieList
import kotlinx.coroutines.flow.flow

@Composable
fun HomeScreen(
    viewModel:HomeViewModel = hiltViewModel(),
    navigateToSelectedActor: (Int) -> Unit,
    navigateToSearch: (SearchType) -> Unit,
    navigateToAbout: () -> Unit,
    navigateToSelectedMovie: (Int) -> Unit,
    navigateToFavorite: () -> Unit,


) {
    val navigateToSearchBySearchType by viewModel.updateHomeSearchType.observeAsState(SearchType.ACTORS)
    HomeScreenUI(
        modifier = Modifier,
        navigateToFavorite = navigateToFavorite,
        navigateToSearch = navigateToSearch,
        navigateToSearchBySearchType = navigateToSearchBySearchType,
        navigateToSelectedActor = navigateToSelectedActor,
        navigateToSelectedMovie = navigateToSelectedMovie,
        uiState = viewModel.uiState,
        sheetUiState = viewModel.sheetUIState,
        updateHomeSearchType = { searchType:SearchType ->
            viewModel.updateHomeSearchType(searchType)

        },
        navigateToAbout = navigateToAbout
    )

}
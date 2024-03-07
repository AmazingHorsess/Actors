package com.example.actors.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.actors.R

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToSelectedActor: (Int) -> Unit,
    navigateToSelectedMovie: (Int) -> Unit
) {
    val uiState = viewModel.uiState

    val navigateToSearchBySearchType = when (viewModel.searchType) {
        SearchType.ACTORS -> navigateToSelectedActor
        SearchType.MOVIES -> navigateToSelectedMovie
    }

    val searchHint = when (viewModel.searchType) {
        SearchType.ACTORS -> stringResource(R.string.hint_search_query_actors)
        SearchType.MOVIES -> stringResource(R.string.hint_search_query_movies)
    }

    SearchScreenUI(
        navigateUp = navigateUp,
        navigateToSearchBySearchType = navigateToSearchBySearchType,
        searchHint = searchHint,
        onSearchQueryChange = { query -> viewModel.performQuery(query) },
        uiState = uiState
    )
}
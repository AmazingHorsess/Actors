package com.example.actors.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actors.domain.repository.search.SearchRepository
import com.example.actors.ui.navigation.AppDestinations.SEARCH_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchRepository: SearchRepository
) : ViewModel() {

    val searchType: SearchType = checkNotNull(savedStateHandle[SEARCH_TYPE])

    var uiState: SearchUIState by mutableStateOf(SearchUIState.ActorSearch())
        private set

    /**
     * @param searchQuery user entered query in text field.
     * This function triggers everytime user makes query change.
     */
    fun performQuery(searchQuery: String) {
        viewModelScope.launch {
            when (searchType) {
                SearchType.ACTORS -> {
                    uiState = SearchUIState.ActorSearch(isSearchingResults = true)
                    val searchData = searchRepository.getSearchableActorsData(searchQuery)
                    uiState = (uiState as SearchUIState.ActorSearch).copy(
                        actorList = searchData,
                        isSearchingResults = false
                    )
                }
                SearchType.MOVIES -> {
                    uiState = SearchUIState.MovieSearch(isSearchingResults = true)
                    val searchData = searchRepository.getSearchableMoviesData(searchQuery)
                    uiState = (uiState as SearchUIState.MovieSearch).copy(
                        movieList = searchData,
                        isSearchingResults = false
                    )
                }
            }
        }
    }
}
package com.example.actors.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actors.domain.GetPagedMovies
import com.example.actors.domain.repository.actor.ActorRepository
import com.example.actors.domain.repository.movies.MovieRepository
import com.example.actors.ui.screens.search.SearchType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val actorRepository: ActorRepository,
    private val movieRepository: MovieRepository,
    private val getPagedMovies: GetPagedMovies,


) :ViewModel(){

    var uiState by mutableStateOf(HomeUIState())
        private set

    var sheetUIState by mutableStateOf(HomeSheetUIState())
        private set

    init {

        viewModelScope.launch {
            try {
                startFetchingActors()
            } catch (e: IOException){
                Log.d("HomeViewModel",e.toString())
            }
        }
    }


    private val _updateHomeSearchType = MutableLiveData<SearchType>()
    val updateHomeSearchType: LiveData<SearchType> = _updateHomeSearchType

    fun updateHomeSearchType(searchType: SearchType){
        when(searchType){
            SearchType.ACTORS -> _updateHomeSearchType.value = SearchType.ACTORS
            SearchType.MOVIES -> _updateHomeSearchType.value = SearchType.MOVIES
        }
    }
    private suspend fun startFetchingActors(){
        uiState = HomeUIState(isFetchingActors = true)
        uiState = HomeUIState(
            popularActorList = actorRepository.popularActorsList(2),
            trendingActorList = actorRepository.trendingActorsList(2),
            isFetchingActors = false,
            upcomingMoviesList = movieRepository.getUpcomingMovie(),
            nowPlayingMoviesList = getPagedMovies(viewModelScope)
        )
    }

}

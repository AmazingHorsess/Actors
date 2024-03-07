package com.example.actors.ui.screens.actorDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.toFavoriteActor
import com.example.actors.domain.repository.actor.ActorRepository
import com.example.actors.domain.repository.movies.MovieRepository
import com.example.actors.domain.useCase.RemoveActorsFromFavoritesUseCase
import com.example.actors.ui.navigation.AppDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ActorDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val actorRepository: ActorRepository,
    private val removeActorsFromFavoritesUseCase: RemoveActorsFromFavoritesUseCase,
    savedStateHandle: SavedStateHandle

): ViewModel() {
    private val actorId: Int = checkNotNull(savedStateHandle[AppDestinations.ACTOR_DETAIL_ID_KEY])

    var detailUIState by mutableStateOf(ActorDetailsUIState(actorData = null))
        private set

    var sheetUIState by mutableStateOf(ActorDetailsSheetUIState())
        private set

    val isFavoriteMovie: LiveData<Int> = actorRepository.isFavoriteActor(actorId)

    private suspend fun startFetchingDetails() {
        detailUIState = ActorDetailsUIState(isFetchingDetails = true, actorData = null)
        val actorData = actorRepository.getSelectedActorData(actorId)
        val castData = actorRepository.getCastData(actorId)
        detailUIState = ActorDetailsUIState(
            castList = castData,
            actorData = actorData,
            isFetchingDetails = false
        )
    }

    init {
        viewModelScope.launch {
            try {
                startFetchingDetails()
            } catch (e: IOException) {

            }
        }
    }

    /**
     * @param movieId for querying selected movie details.
     * This function will be triggered only when user clicks any movie items.
     * Updates the data values to show in modal sheet.
     */
    fun getSelectedMovieDetails(
        movieId: Int?
    ) {
        viewModelScope.launch {
            try {
                movieId?.let { id ->
                    val movieData = movieRepository.getSelectedMovieData(id)
                    sheetUIState = ActorDetailsSheetUIState(selectedMovieDetails = movieData)
                }
            } catch (e: IOException) {


            }
        }
    }

    fun addActorToFavorites() {
        viewModelScope.launch {
            val actor: ActorDetail? = detailUIState.actorData
            if (actor != null) {
                actorRepository.addActorsToFavorite(
                    actor.toFavoriteActor()
                )
            } else {

            }
        }
    }

    fun removeActorFromFavorites() {
        viewModelScope.launch {
            val actor: ActorDetail? = detailUIState.actorData
            if (actor != null) {
                removeActorsFromFavoritesUseCase(
                    actor.toFavoriteActor()
                )
            } else {

            }
        }
    }
}



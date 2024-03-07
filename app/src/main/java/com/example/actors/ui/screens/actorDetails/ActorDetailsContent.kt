package com.example.actors.ui.screens.actorDetails

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.actors.ui.screens.actorDetails.composables.ActorBiography
import com.example.actors.ui.screens.actorDetails.composables.ActorCastedMovies
import com.example.actors.ui.screens.actorDetails.composables.ActorInfoHeader
import com.example.actors.ui.screens.actorDetails.composables.ActorProfileRound
import com.example.actors.ui.screens.actorDetails.composables.ActorRoundProfilePreview
import kotlinx.coroutines.Job

@Composable
fun ActorDetailsContent(
    navigateUp: () -> Unit,
    detailUiState: ActorDetailsUIState,
    openActorDetailsBottomSheet: () -> Job,
    getSelectedMovieDetail: (Int) -> Unit,
    showFab: MutableState<Boolean>

    ) {
    val actorData = detailUiState.actorData
    val listState = rememberLazyListState()

    Spacer(modifier = Modifier.padding(top = 16.dp))
    ActorProfileRound(profileUrl = "${actorData?.profileUrl}")
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    showFab.value = !listState.isScrollInProgress
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ActorInfoHeader(actorData)
        }
        item {
            ActorCastedMovies(
                detailUiState = detailUiState,
                openActorDetailsBottomSheet = openActorDetailsBottomSheet,
                getSelectedMovieDetails =  getSelectedMovieDetail
            )
        }
        item {
            ActorBiography(actorData?.biography)
        }
    }

}
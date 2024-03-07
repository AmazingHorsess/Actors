package com.example.actors.ui.screens.movieDetails.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.data.datasource.fake.fakeMovieDetail
import com.example.actors.data.model.BottomSheetType
import com.example.actors.data.model.Cast
import com.example.actors.data.model.Flatrate
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.Trailer
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.screens.movieDetails.MovieDetailsUIState
import com.example.actors.ui.theme.ActorsTheme
import kotlinx.coroutines.Job

@Composable
fun GetMovieCast(
    uiState: MovieDetailsUIState,
    openMovieDetailsBottomSheet: () -> Job,
    selectBottomSheetCallback: (BottomSheetType) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ){
        items(
            items = uiState.movieCast,
            key = {it.actorId}
        ){cast->
            ItemCast(
                cast = cast,
                movieDetailsUIState = uiState,
                openMovieDetailsBottomSheet = openMovieDetailsBottomSheet,
                selectBottomSheetCallBack = selectBottomSheetCallback
            )


        }

    }
}

@Composable
private fun ItemCast(
    cast: Cast,
    movieDetailsUIState: MovieDetailsUIState,
    openMovieDetailsBottomSheet: () -> Job,
    selectBottomSheetCallBack: (BottomSheetType) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(120.dp)
            .clickable {
                openMovieDetailsBottomSheet()
                selectBottomSheetCallBack(BottomSheetType.ActorDetailBottomSheet.apply {
                    movieOrActorId = cast.actorId
                })
            }
    ) {
        LoadNetworkImage(
            imageUrl =cast.castProfilePath ,
            contentDescription = stringResource(id = R.string.cd_actor_image) ,
            shape = CircleShape,
            modifier = Modifier
                .size(120.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = cast.castName,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun GetMovieCastLightPreview() {
    ActorsTheme(darkTheme = false) {
        GetMovieCast(
            uiState = MovieDetailsUIState(
                movieData = fakeMovieDetail,
                similarMovies = listOf(),
                recommendedMovies = listOf(),
                movieCast = listOf(),
                isFetchingDetails = false,
                movieProviders = MovieProvider(
                    arrayListOf(Flatrate("", 1, ""))
                ),
            ),
            openMovieDetailsBottomSheet = { Job() },
            selectBottomSheetCallback = { }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun GetMovieCastDarkPreview() {
    ActorsTheme(darkTheme = true) {
        GetMovieCast(
            uiState = MovieDetailsUIState(
                movieData = fakeMovieDetail,
                similarMovies = listOf(),
                recommendedMovies = listOf(),
                movieCast = listOf(),
                isFetchingDetails = false,
                movieProviders = MovieProvider(
                    arrayListOf(Flatrate("", 1, ""))
                ),
            ),
            openMovieDetailsBottomSheet = { Job() },
            selectBottomSheetCallback = {}
        )
    }
}
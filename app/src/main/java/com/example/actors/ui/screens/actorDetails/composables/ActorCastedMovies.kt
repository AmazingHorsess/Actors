package com.example.actors.ui.screens.actorDetails.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.actors.R
import androidx.compose.foundation.lazy.items
import com.example.actors.data.model.Movie
import com.example.actors.ui.components.CategoryTitle
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.screens.actorDetails.ActorDetailsUIState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ActorCastedMovies(
    detailUiState: ActorDetailsUIState,
    openActorDetailsBottomSheet: () -> Job,
    getSelectedMovieDetails: (Int) -> Unit

){
//    val cast: Flow<List<Movie>> = detailUiState.castList
//
//    Row (
//        verticalAlignment = Alignment.CenterVertically
//    ){
//        Image(
//            painter = painterResource(id = R.drawable.ic_movie_cast),
//            contentDescription = stringResource(id = R.string.cd_cast_icon),
//            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
//            alpha = 0.5f,
//            modifier = Modifier
//                .padding(start = 12.dp)
//                .size(36.dp)
//        )
//
//        CategoryTitle(title = stringResource(id = R.string.cast_movie_title))
//
//    }
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
//        contentPadding = PaddingValues(16.dp)
//    ) {
//        items(
//            items = cast,
//            key = { it.movieId }
//        ) { movie ->
//            LoadNetworkImage(
//                imageUrl = movie.posterPathUrl,
//                contentDescription = stringResource(R.string.cd_movie_poster),
//                shape = MaterialTheme.shapes.medium,
//                modifier = Modifier
//                    .size(100.dp, 150.dp)
//                    .clickable {
//                        getSelectedMovieDetails(movie.movieId)
//                        openActorDetailsBottomSheet()
//                    }
//            )
//        }
//    }
}
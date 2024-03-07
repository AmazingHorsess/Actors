package com.example.actors.ui.screens.movieDetails.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.data.datasource.fake.fakeMovieList
import com.example.actors.data.model.BottomSheetType
import com.example.actors.data.model.Movie
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.theme.ActorsTheme
import kotlinx.coroutines.Job

@Composable
fun GetSimilarOrRecommendedMovies(
    movieList: List<Movie>,
    openMovieDetailBottomSheet: () -> Job,
    selectBottomSheetCallBack: (BottomSheetType) -> Unit
    ){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(
            items = movieList,
            key = {it.movieId}
        ){
            movie ->
            LoadNetworkImage(
                imageUrl = movie.posterPathUrl ,
                contentDescription = stringResource(id = R.string.cd_movie_poster),
                shape =  MaterialTheme.shapes.medium,
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .clickable {
                        selectBottomSheetCallBack(BottomSheetType.MovieDetailBottomSheet.apply {
                            movieOrActorId = movie.movieId
                        })
                        openMovieDetailBottomSheet()
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GetSimilarOrRecommendedMoviesPreviewLight(){
    ActorsTheme(darkTheme = false) {
        GetSimilarOrRecommendedMovies(
            movieList = fakeMovieList(),
            openMovieDetailBottomSheet = {  Job() },
            selectBottomSheetCallBack = {

            }
        )


    }
}
@Composable
@Preview(showBackground = true)
fun GetSimilarOrRecommendedMoviesPreviewDark(){
    ActorsTheme(darkTheme = true) {
        GetSimilarOrRecommendedMovies(
            movieList = fakeMovieList(),
            openMovieDetailBottomSheet = {  Job() },
            selectBottomSheetCallBack = {

            }
        )


    }
}
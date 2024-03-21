package com.example.actors.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.data.datasource.fake.fakeMovieList
import com.example.actors.data.model.Movie
import com.example.actors.ui.theme.TmdbTheme

@Composable
fun MovieSearchUI(
    movieList: List<Movie>,
    navigateToSelectedMovie: (Int)-> Unit,
    closeKeyboard: () -> Unit?

){
    LazyColumn(
        modifier = Modifier.padding(bottom = 48.dp)

    ){
        items(movieList){movie->
        ItemSearchItem(
            movie = movie,
            onClickMovie = navigateToSelectedMovie,
            closeKeyboard =  closeKeyboard
        )



        }

    }

}
@Composable
private fun ItemSearchItem(
    movie:Movie,
    onClickMovie: (Int) -> Unit,
    closeKeyboard: () -> Unit?
){
    Text(
        text = movie.movieName,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                closeKeyboard()
                onClickMovie(movie.movieId)
            }
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .wrapContentWidth(Alignment.Start)
    )

}
@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun MovieSearchUIDarkPreview() {
    TmdbTheme(darkTheme = true) {
        MovieSearchUI(
            movieList = fakeMovieList(),
            navigateToSelectedMovie = {},
            closeKeyboard = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieSearchUILightPreview() {
    TmdbTheme(darkTheme = false) {
        MovieSearchUI(
            movieList = fakeMovieList(),
            navigateToSelectedMovie = {},
            closeKeyboard = {}
        )
    }
}
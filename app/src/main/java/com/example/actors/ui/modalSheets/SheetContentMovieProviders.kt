package com.example.actors.ui.modalSheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.data.model.Flatrate

import com.example.actors.data.model.MovieProvider
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.components.SheetHorizontalSeparator
import com.example.actors.ui.screens.movieDetails.composables.FloatingAddToFavoritesButton
import com.example.actors.ui.theme.ActorsTheme

@Composable
fun SheetContentMovieProviders(
    movieProvider: MovieProvider,
    isFavoriteMovie: Boolean,
    addMovieToFavorites: () -> Unit,
    removeMovieFromFavorites: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(MaterialTheme.colorScheme.surface)
            .navigationBarsPadding()
    ) {
        HeaderModalSheet()

        if (movieProvider.flatrate.isNotEmpty()) {
            Streaming(flatrate = movieProvider.flatrate)
        } else {
            NoStreaming()
        }

        FloatingAddToFavoritesButton(
            isFavorite = isFavoriteMovie,
            addToFavorites = addMovieToFavorites,
            removeFromFavorites = removeMovieFromFavorites,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun NoStreaming() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.no_watch_options),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun Streaming(flatrate: ArrayList<Flatrate>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
    ) {
        items(flatrate) { flatrate ->
            LoadNetworkImage(
                imageUrl = flatrate.logo_path,
                contentDescription = "",
                shape = MaterialTheme.shapes.large,
                showAnimProgress = false,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(48.dp)
            )
        }
    }
}

@Composable
private fun HeaderModalSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SheetHorizontalSeparator()
        }
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = stringResource(id = R.string.stream),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
@PreviewLightDark
@Composable
fun PreviewHeaderModalSheet(){
    ActorsTheme {
        SheetContentMovieProviders(
            movieProvider = MovieProvider(
                arrayListOf(Flatrate("", 1, ""))
            ),

            isFavoriteMovie =false ,
            addMovieToFavorites = { /*TODO*/ }) {

        }
    }
    

}

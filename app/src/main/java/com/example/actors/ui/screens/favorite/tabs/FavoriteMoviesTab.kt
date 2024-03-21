package com.example.actors.ui.screens.favorite.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actors.R
import com.example.actors.data.datasource.fake.fakeFavoriteMovieList
import com.example.actors.data.model.FavoriteMovie
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.screens.favorite.NoFavoritesFoundUI
import com.example.actors.ui.theme.TmdbTheme

@Composable
fun FavoriteMoviesTabContent(
    navigateToSelectedMovie: (Int) -> Unit,
    favoriteMovies: List<FavoriteMovie>,
    removeFavoriteMovie: (FavoriteMovie) -> Unit,
) {
    if (favoriteMovies.isEmpty()) {
        NoFavoritesFoundUI()
    }

    val listState = rememberLazyListState()

    LazyColumn(
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        state = listState,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(favoriteMovies) { movieItem ->
            ItemFavoriteMovie(
                movieItem = movieItem,
                onClickMovie = navigateToSelectedMovie,
                removeFavoriteMovie = removeFavoriteMovie
            )
        }
    }
}

@Composable
private fun ItemFavoriteMovie(
    movieItem: FavoriteMovie,
    onClickMovie: (Int) -> Unit,
    removeFavoriteMovie: (FavoriteMovie) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        LoadNetworkImage(
            imageUrl = movieItem.bannerUrl,
            contentDescription = stringResource(R.string.cd_movie_poster),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { onClickMovie(movieItem.movieId) }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = movieItem.movieName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                lineHeight = 20.sp,
                modifier = Modifier.weight(0.8f)
            )
            IconButton(
                onClick = { removeFavoriteMovie(movieItem) },
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteMoviesTabContentLightPreview() {
    TmdbTheme(darkTheme = false) {
        FavoriteMoviesTabContent(
            navigateToSelectedMovie = {},
            favoriteMovies = fakeFavoriteMovieList(),
            removeFavoriteMovie = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun FavoriteMoviesTabContentDarkPreview() {
    TmdbTheme(darkTheme = true) {
        FavoriteMoviesTabContent(
            navigateToSelectedMovie = {},
            favoriteMovies = fakeFavoriteMovieList(),
            removeFavoriteMovie = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteMoviesTabContentNoFavoritesLightPreview() {
    TmdbTheme(darkTheme = false) {
        FavoriteMoviesTabContent(
            navigateToSelectedMovie = {},
            favoriteMovies = emptyList(),
            removeFavoriteMovie = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun FavoriteMoviesTabContentNoFavoritesDarkPreview() {
    TmdbTheme(darkTheme = true) {
        FavoriteMoviesTabContent(
            navigateToSelectedMovie = {},
            favoriteMovies = emptyList(),
            removeFavoriteMovie = {}
        )
    }
}
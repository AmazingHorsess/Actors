package com.example.actors.ui.screens.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.FavoriteMovie
import com.example.actors.ui.components.AppDivider
import com.example.actors.ui.components.TabItem
import com.example.actors.ui.components.TabsContainer
import com.example.actors.ui.screens.favorite.tabs.FavoriteActorsTabContent
import com.example.actors.ui.screens.favorite.tabs.FavoriteMoviesTabContent
import com.example.actors.ui.theme.TmdbTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreenUI(
    navigateUp: () -> Unit,
    favoriteMovies: List<FavoriteMovie>,
    navigateToSelectedMovie: (Int) -> Unit,
    removeFavoriteMovie: (FavoriteMovie) -> Unit,
    navigateToSelectedActor: (Int) -> Unit,
    favoriteActors: List<FavoriteActor>,
    removeFavoriteActor: (FavoriteActor) -> Unit,

    ){
    val favoriteTabs = listOf(
        TabItem("Actors"),
        TabItem("Movies")
    )
    val favoritesPagerState = rememberPagerState(pageCount = {favoriteTabs.size})
    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            topBar = {
                FavoritesTopAppBar(
                    navigateUp = navigateUp
                )
            }

        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                TabsContainer(
                    tabs = favoriteTabs,
                    pagerState = favoritesPagerState
                )
                AppDivider(thickness = 1.dp, verticalPadding = 0.dp)
                HorizontalPager(
                    state = favoritesPagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()

                ) { index ->
                    when(index){
                        0 -> FavoriteActorsTabContent(
                            navigateToSelectedActor = navigateToSelectedActor,
                            favoriteActors = favoriteActors,
                            removeFavoriteActor = removeFavoriteActor
                        )
                        1 -> FavoriteMoviesTabContent(
                            navigateToSelectedMovie =navigateToSelectedMovie ,
                            favoriteMovies = favoriteMovies ,
                            removeFavoriteMovie = removeFavoriteMovie
                        )
                        2 -> FeatureComingSoonTextUI()

                    }

                }

            }

        }

    }
}
@Composable
private fun FeatureComingSoonTextUI() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Feature Coming Soon",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 40.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun FavoriteScreenUILightPreview() {
    TmdbTheme(darkTheme = false) {
        FavoriteScreenUI(
            favoriteMovies = emptyList(),
            navigateToSelectedMovie = {},
            removeFavoriteMovie = {},
            navigateToSelectedActor = {},
            favoriteActors = emptyList(),
            removeFavoriteActor = {},
            navigateUp = {}
        )
    }
}

@Preview
@Composable
private fun FavoriteScreenUIDarkPreview() {
    TmdbTheme(darkTheme = true) {
        FavoriteScreenUI(
            favoriteMovies = emptyList(),
            navigateToSelectedMovie = {},
            removeFavoriteMovie = {},
            navigateToSelectedActor = {},
            favoriteActors = emptyList(),
            removeFavoriteActor = {},
            navigateUp = {}
        )
    }
}
package com.example.actors.ui.screens.favorite.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.data.datasource.fake.fakeFavoriteActorsList
import com.example.actors.data.model.FavoriteActor
import com.example.actors.ui.components.ImageBackgroundThemeGenerator
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.components.verticalGradientScrim
import com.example.actors.ui.screens.favorite.NoFavoritesFoundUI
import com.example.actors.ui.theme.TmdbTheme
import com.example.actors.utils.getPlaceOfBirth

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteActorsTabContent(
    navigateToSelectedActor: (Int) -> Unit,
    favoriteActors: List<FavoriteActor>,
    removeFavoriteActor: (FavoriteActor) -> Unit,
){
    if (favoriteActors.isEmpty()){
        NoFavoritesFoundUI()
    }
    val listState = rememberPagerState(pageCount =  {favoriteActors.size})

    VerticalPager(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fixed(512.dp),
        pageSpacing = 24.dp,
        contentPadding = PaddingValues(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 48.dp)
    ) { currentPage ->
        ItemFavoriteActor(
            actorItem = favoriteActors[currentPage],
            onClickActor = navigateToSelectedActor,
            removeFavoriteActor = removeFavoriteActor
        )

    }




}
@Composable
private fun ItemFavoriteActor(
    actorItem: FavoriteActor,
    onClickActor: (Int) -> Unit,
    removeFavoriteActor: (FavoriteActor) -> Unit
){
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.large)
    ) {
        LoadNetworkImage(
            imageUrl = actorItem.profileUrl,
            contentDescription = "Actor Image",
            shape = RectangleShape,
            showAnimProgress = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(512.dp)
                .clickable {
                    onClickActor(actorItem.actorId)
                }
        )

        ImageBackgroundThemeGenerator(
            imageUrl = actorItem.profileUrl,
            backgroundColor = MaterialTheme.colorScheme.primary
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(124.dp)
                    .verticalGradientScrim(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f),
                        startYPercentage = 0f,
                        endYPercentage = 1f
                    )
            )
        }

        ImageBackgroundThemeGenerator(
            imageUrl = actorItem.profileUrl,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp, bottom = 20.dp, end = 12.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = actorItem.actorName,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Text(
                        text = "${getPlaceOfBirth(actorItem.placeOfBirth)}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }

                IconButton(
                    onClick = { removeFavoriteActor(actorItem) },
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun FavoriteActorsTabContentPreviewLightPreview() {
    TmdbTheme(darkTheme = false) {
        FavoriteActorsTabContent(
            navigateToSelectedActor = {},
            favoriteActors = fakeFavoriteActorsList(),
            removeFavoriteActor = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun FavoriteActorsTabContentPreviewDarkPreview() {
    TmdbTheme(darkTheme = true) {
        FavoriteActorsTabContent(
            navigateToSelectedActor = {},
            favoriteActors = fakeFavoriteActorsList(),
            removeFavoriteActor = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteActorsTabContentNoFavoritesPreviewLightPreview() {
    TmdbTheme(darkTheme = false) {
        FavoriteActorsTabContent(
            navigateToSelectedActor = {},
            favoriteActors = emptyList(),
            removeFavoriteActor = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun FavoriteActorsTabContentNoFavoritesPreviewDarkPreview() {
    TmdbTheme(darkTheme = true) {
        FavoriteActorsTabContent(
            navigateToSelectedActor = {},
            favoriteActors = emptyList(),
            removeFavoriteActor = {}
        )
    }
}
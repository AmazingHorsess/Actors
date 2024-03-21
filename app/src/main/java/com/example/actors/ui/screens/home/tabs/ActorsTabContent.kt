package com.example.actors.ui.screens.home.tabs

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.data.datasource.network.retrofit.model.ActorDetailsResponse
import com.example.actors.data.datasource.network.retrofit.model.TrendingActorResponse
import com.example.actors.data.datasource.network.retrofit.service.NetworkResult
import com.example.actors.data.model.Actor
import com.example.actors.ui.components.AppDivider
import com.example.actors.ui.components.CategoryTitle
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.components.ShowProgressIndicator
import com.example.actors.ui.screens.home.HomeUIState
import kotlinx.coroutines.flow.Flow

@Composable
fun ActorsTabContent(
    homeUIState: HomeUIState,
    navigateToSelectedActor: (Int) -> Unit,
    popularActorsListState: LazyListState,
    trendingActorsListState: LazyListState

){
    ShowProgressIndicator(isLoadingData = homeUIState.isFetchingActors)

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.fillMaxSize()
    ){
        item{
            CategoryTitle(stringResource(R.string.category_actors_popular))
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            ItemActorList(
                actorList = homeUIState.trendingActorList ,
                navigateToSelectedActor = navigateToSelectedActor ,
                actorsListState = popularActorsListState
            )
            AppDivider(verticalPadding = 32.dp)
            CategoryTitle(title = stringResource(R.string.category_actors_trending))
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            ItemActorList(
                actorList = homeUIState.trendingActorList,
                navigateToSelectedActor = navigateToSelectedActor ,
                actorsListState = trendingActorsListState
            )
        }
    }

}

@Composable
private fun ItemActorList(
    actorList: Flow<NetworkResult<TrendingActorResponse>>,
    navigateToSelectedActor: (Int) -> Unit,
    actorsListState: LazyListState
) {
    // Your LazyRow composable code goes here
    LazyRow(
        state = actorsListState,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

    }
}
@Composable
private fun ItemActor(
    actor:ActorDetailsResponse,
    onClickActor: (Int) -> Unit
){
    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = Modifier
            .width(150.dp)
            .clip(shape = MaterialTheme.shapes.large)
            .clickable(onClick = { onClickActor(actor.id) })
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            LoadNetworkImage(
                imageUrl = actor.profilePath ,
                contentDescription = stringResource(R.string.cd_actor_image) ,
                shape = CircleShape,
                modifier = Modifier
                    .size(120.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = actor.name,
                style = MaterialTheme.typography.bodyLarge,

                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
        }
    }
}
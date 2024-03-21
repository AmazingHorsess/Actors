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
import com.example.actors.data.datasource.fake.fakeActorsList
import com.example.actors.data.model.Actor
import com.example.actors.ui.theme.TmdbTheme

@Composable
fun ActorSearchUI(
    actorsList: List<Actor>,
    navigateToSelectedActor: (Int) -> Unit,
    closeKeyboard: () -> Unit?
){
    LazyColumn(
        modifier = Modifier.padding(bottom = 48.dp)

    ){
        items(actorsList
        ){ actor ->
            ItemSearchActor(
                actor = actor,
                onClickActor = navigateToSelectedActor
                , closeKeyboard =closeKeyboard )

        }


    }


}

@Composable
private fun ItemSearchActor(
    actor:Actor,
    onClickActor: (Int) -> Unit,
    closeKeyboard: () -> Unit?
){
    Text(
        text = actor.actorName,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                closeKeyboard()
                onClickActor(actor.actorId)
            }
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .wrapContentWidth(Alignment.Start)
    )
}
@Preview(
    showBackground = true,
    backgroundColor =  0XFF211a18
)
@Composable
private fun ActorSearchUIDarkPreview(){
    TmdbTheme(darkTheme = true) {
        ActorSearchUI(
            actorsList = fakeActorsList(),
            navigateToSelectedActor ={},
            closeKeyboard = {}
        )
    }
}
@Preview(
    showBackground = true,

)
@Composable
private fun ActorSearchUILightPreview(){
    TmdbTheme(darkTheme = false) {
        ActorSearchUI(
            actorsList = fakeActorsList(),
            navigateToSelectedActor ={},
            closeKeyboard = {}
        )
    }
}

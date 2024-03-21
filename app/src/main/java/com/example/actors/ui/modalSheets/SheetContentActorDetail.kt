package com.example.actors.ui.modalSheets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actors.data.datasource.network.retrofit.model.ActorDetailsResponse
import com.example.actors.data.datasource.network.retrofit.service.NetworkResult
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.components.SheetHorizontalSeparator
import com.example.actors.ui.screens.actorDetails.composables.ActorInfoHeader
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SheetContentActorDetail(
    actor: Flow<NetworkResult<Response<ActorDetailsResponse>>>?,
    navigateToSelectedActor: (Int) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()

    ){
        stickyHeader {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                SheetHorizontalSeparator()
                Spacer(modifier = Modifier.height(24.dp))
               // ActorProfileImage(
                //    actor,
                //    actor?.profilePath
               //     , navigateToSelectedActor,
              //  )
                Spacer(modifier = Modifier.height(16.dp))
               // ActorNameText(actor?.name.toString())
                Spacer(modifier = Modifier.height(16.dp))
               // ActorInfoHeader(actorData = actor)
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            //ActorBiographyText(actor?.biography.toString())
            Spacer(modifier = Modifier.height(24.dp))

        }
    }

}
@Composable
private fun ActorProfileImage(
    actor: ActorDetailsResponse?,
    profileUrl: String?,
    navigateToSelectedActor: (Int) ->Unit,
){
    LoadNetworkImage(
        imageUrl = profileUrl,
        contentDescription = "test",
        shape = CircleShape,
        modifier = Modifier
            .size(120.dp)
            .clickable{
               if (actor != null){
                   navigateToSelectedActor(actor.id)
               }
            }

            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = CircleShape

            )
    )
}
@Composable
private fun ActorNameText(
    actorName: String
){
    Text(
        text = actorName,
        color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
        modifier = Modifier.padding(horizontal = 24.dp),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun ActorBiographyText(
    biography: String
){
    Text(
        text = biography,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),

        lineHeight = 20.sp,
        color = MaterialTheme.colorScheme.onBackground.copy(0.8f),
        textAlign = TextAlign.Justify,
        fontSize = 16.sp
    )
}
//@Preview
//@Composable
//private fun SheetContentActorDetailsLightPreview(){
//    ActorsTheme(darkTheme = false) {
//        SheetContentActorDetail(
//            actor = fakeActorDetail,
//            navigateToSelectedActor = {}
//        )
//
//
//    }
//}
//@Preview
//@Composable
//private fun SheetContentActorDetailsDarkPreview(){
//    ActorsTheme(darkTheme = true) {
//        SheetContentActorDetail(
//            actor = fakeActorDetail,
//            navigateToSelectedActor = {}
//        )
//
//    }
//}
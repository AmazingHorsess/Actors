package com.example.actors.ui.screens.actorDetails.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.actors.R
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.components.verticalGradientScrim
import com.example.actors.ui.theme.ActorsTheme

@Composable
internal fun ActorBackgroundWithGradientForeground(
    imageUrl:String,
    modifier: Modifier = Modifier

){
    Box{
        LoadNetworkImage(
            imageUrl = imageUrl ,
            contentDescription = stringResource(id = R.string.cd_actor_banner),
            shape = RectangleShape,
            showAnimProgress = false,
            modifier = modifier
                .fillMaxSize()
                .alpha(0.5f)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalGradientScrim(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.50f),
                    startYPercentage = 1f, endYPercentage = 0f
                )
        )

    }

}
@Composable
@Preview
fun ActorBackgroundWithGradientForegroundPreview(){
    ActorsTheme {
        ActorBackgroundWithGradientForeground(imageUrl = "testurl")
//
    }
}
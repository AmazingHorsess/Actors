    package com.example.actors.ui.screens.actorDetails.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.theme.ActorsTheme

@Composable
internal fun ActorProfileRound(
    profileUrl: String,
    size: Dp = 120.dp,
    profilePreview: Int = R.drawable.adele
) {
    Box (
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LoadNetworkImage(
            imageUrl = profileUrl,
            contentDescription = stringResource(id = R.string.cd_actor_image),
            shape = CircleShape,
            modifier = Modifier
                .size(size)
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                )
        )


    }
}

@Preview
@Composable
fun ActorRoundProfilePreview() {
    ActorsTheme {
        ActorProfileRound(profileUrl = "testUrl")
    }

}


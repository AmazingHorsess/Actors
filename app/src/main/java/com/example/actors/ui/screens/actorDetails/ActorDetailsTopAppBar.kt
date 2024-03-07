package com.example.actors.ui.screens.actorDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.theme.ActorsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ActorDetailsTopAppBar(
    navigateUp: () -> Unit,
    title: String
){
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .padding(start = 4.dp)
    ) {
        IconButton(
            onClick = navigateUp,
            modifier = Modifier.align(alignment = Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = stringResource(id = R.string.cd_up_button)
            )
        }

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
        )
    }

}
@Composable
@Preview(showBackground = true)
fun ActorTopAppBarPreviewLight(){
    ActorsTheme(darkTheme = false) {
        ActorDetailsTopAppBar(navigateUp = { }, title ="Monnica Belluci" )

    }

}
@Composable
@Preview(showBackground = true)
fun ActorTopAppBarPreviewDark(){
    ActorsTheme(darkTheme = true) {
        ActorDetailsTopAppBar(navigateUp = { }, title ="Monnica Belluci" )

    }

}
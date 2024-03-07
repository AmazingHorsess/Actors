package com.example.actors.ui.screens.favorite

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.theme.ActorsTheme


@Composable
fun FavoritesTopAppBar(
    navigateUp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        IconButton(
            onClick = navigateUp,
            modifier = Modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = stringResource(id = R.string.cd_up_button)
            )
        }

        Text(
            text = "Favorites",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesTopAppBarLightPreview() {
    ActorsTheme(darkTheme = false) {
        FavoritesTopAppBar { }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun FavoritesTopAppBarDarkPreview() {
    ActorsTheme(darkTheme = true) {
        FavoritesTopAppBar { }
    }
}
package com.example.actors.ui.screens.home.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.screens.home.HomeSheetUIState
import com.example.actors.ui.theme.ActorsTheme


@Composable
fun TvShowsTabContent(
    homeSheetUIState: HomeSheetUIState
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_tv_show),
                contentDescription = "",
                modifier = Modifier.size(240.dp)
            )

            Text(
                text = "Feature Coming Soon",
                style = MaterialTheme.typography.headlineSmall,

                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 40.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenUIPreview() {
    ActorsTheme {
        TvShowsTabContent(
            homeSheetUIState = HomeSheetUIState()
        )
    }
}
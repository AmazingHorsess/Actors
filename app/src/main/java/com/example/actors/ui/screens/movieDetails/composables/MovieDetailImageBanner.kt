package com.example.actors.ui.screens.movieDetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.actors.ui.components.LoadNetworkImage

@Composable
fun MovieDetailImageBanner(
    bannerUrl: String?,

){

        LoadNetworkImage(
            imageUrl = bannerUrl?.toString() ,
            contentDescription = "" ,
            shape = MaterialTheme.shapes.medium,
            showAnimProgress = false,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .aspectRatio(1.8f)
                .shadow(elevation = 8.dp)
        )




}
package com.example.actors.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.actors.ui.animations.AnimatedSearch
import com.example.actors.ui.animations.InfinitelyFlowingCircles

@Composable
fun ShowProgressIndicator(
    isLoadingData:Boolean
){
    if (isLoadingData){
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
@Composable
fun ShowSearchProgress(
    isLoadingData: Boolean
) {
    if (isLoadingData) {
        InfinitelyFlowingCircles()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 28.dp, end = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedSearch()
        }
    }
}
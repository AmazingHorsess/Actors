package com.example.actors.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.ui.theme.TmdbTheme

@Composable
fun HomeSnackbar(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier

){
    SnackbarHost(hostState) {data->
        Snackbar (
            snackbarData = data,
            modifier = modifier.padding(48.dp)
            )
    }
}

@Preview
@Composable
fun HomeSnackbarPreview(){
    TmdbTheme {
        HomeSnackbar(hostState = SnackbarHostState())

    }
}
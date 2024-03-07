package com.example.actors.ui.screens.movieDetails.composables
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.actors.R

@Composable
fun FloatingAddToFavoritesButton(
    isFavorite: Boolean,
    addToFavorites: () -> Unit,
    removeFromFavorites: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fabState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ExtendedFloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.navigationBarsPadding(),
            onClick = {
                if (!isFavorite) {
                    addToFavorites()
                } else {
                    removeFromFavorites()
                }
            },
            icon = {
                Icon(
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    imageVector = if (isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    }
                )
            },
            text = {
                AnimatedVisibility(
                    visibleState = fabState
                ) {
                    Text(
                        text = getFavoriteText(isFavorite),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        )
    }
}

@Composable
private fun getFavoriteText(
    isFavoriteMovie: Boolean
): String {
    return if (!isFavoriteMovie) {
        stringResource(R.string.add_to_favorites_text)
    } else {
        stringResource(R.string.remove_from_favorites_text)
    }
}
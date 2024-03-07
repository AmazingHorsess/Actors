package com.example.actors.ui.components

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.utils.NetworkManager
import com.example.actors.utils.isTmdbApiKeyNotValid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LaunchSnackBar(
    snackbarHostState: SnackbarHostState,
    snackBarMessage: String,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    LaunchedEffect(scope) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = snackBarMessage,
                duration = SnackbarDuration.Indefinite
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IfOfflineShowSnackbar(
    snackbarHostState: SnackbarHostState,
    context: Context = LocalContext.current
) {
    val isOnline = NetworkManager(context).checkForActiveNetwork()
    if (!isOnline) {
        LaunchSnackBar(
            snackbarHostState,
            context.getString(R.string.offline_snackbar_message)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiKeyMissingShowSnackbar(
    snackbarHostState: SnackbarHostState,
    context: Context = LocalContext.current
) {
    if (isTmdbApiKeyNotValid()) {
        LaunchSnackBar(
            snackbarHostState,
            context.getString(R.string.missing_api_key_snackbar_message)
        )
    }
}
@Composable
fun ErrorTrailerLoadingShowSnackbar(
    snackbarHostState: SnackbarHostState,
    context: Context = LocalContext.current
) {
    if (isTmdbApiKeyNotValid()) {
        LaunchSnackBar(
            snackbarHostState,
            context.getString(R.string.error_while_loading_trailer_video)
        )
    }
}

@Composable
fun AppDivider(
    verticalPadding: Dp,
    thickness: Dp = 1.dp
) {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = verticalPadding),
        thickness = thickness,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
    )
}

@Composable
fun CategoryTitle(
    title: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    alpha: Float = 0.5f,
    startPadding: Dp = 20.dp,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        color = textColor,
        modifier = Modifier
            .padding(
                start = startPadding,
                top = topPadding,
                bottom = bottomPadding
            )
            .alpha(alpha)
    )
}
object TransparentRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f,
    )
}

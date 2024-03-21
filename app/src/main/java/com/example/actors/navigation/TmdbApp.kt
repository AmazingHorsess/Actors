package com.example.actors.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.actors.R
import com.example.actors.ui.theme.TmdbTheme
import com.example.core.designsystem.TmdbNavigationBar
import com.example.core.designsystem.TmdbNavigationItem

@Composable
fun TmdbApp (appState: TmdbAppState){

    TmdbTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.testTag("tag")
        ) {
            val snackBarHostState = remember { SnackbarHostState() }

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            val notConnectedMessage = stringResource(id = R.string.not_connected)

            LaunchedEffect(isOffline){
                if (isOffline){
                    snackBarHostState.showSnackbar(
                        message = notConnectedMessage,
                        duration = SnackbarDuration.Indefinite
                    )
                }

            }
            Scaffold(
                modifier = Modifier,
                snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                bottomBar = {
                    TmdbBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination ,
                    currentDestination = appState.currentDestination
                    )
                }
            ) {paddingValues ->
                TmdbNavHost(
                    modifier = Modifier.padding(paddingValues),
                    appState = appState
                    , onShowSnackBar = { message, action ->
                        snackBarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = SnackbarDuration.Short
                        ) == SnackbarResult.ActionPerformed

                    }
                )

            }
        }


    }


}

@Composable
private fun TmdbBottomBar(
    destinations: List<TopLevelDestinations>,
    onNavigateToDestination: (TopLevelDestinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
){
    TmdbNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach{destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            TmdbNavigationItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                       Icon(
                           imageVector = destination.unselectedIcon,
                           contentDescription = null
                       )
                },
                selectedIcon = { 
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null
                    )

                    
                },
                label = { Text(text = "test")},
                
            )
        }
    }
}
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
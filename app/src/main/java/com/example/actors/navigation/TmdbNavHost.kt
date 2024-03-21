package com.example.actors.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import core.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun TmdbNavHost(
    appState: TmdbAppState,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = ACTORS_ROUTE
    

){
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        actorsScreen (onActorClick = {})
        moviesScreen {  }
        tvShowsScreen {  }
        myListScreen {  }
    }

}

@Composable
fun rememberTmdbAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    networkMonitor: NetworkMonitor,
    ): TmdbAppState{
    return remember(
        navController,
        coroutineScope,
        networkMonitor

    ){
        TmdbAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            networkMonitor = networkMonitor
        )
    }

}
@Stable
class TmdbAppState(
    val navController:NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor
){
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestinations: TopLevelDestinations?
        @Composable get() = when (currentDestination?.route){
            ACTORS_ROUTE -> TopLevelDestinations.ACTORS
            MOVIES_ROUTE ->TopLevelDestinations.MOVIES
            TV_SHOWS_ROUTE -> TopLevelDestinations.TVSHOWS
            MY_LIST_ROUTE -> TopLevelDestinations.MYLIST
            else -> null

        }
    val topLevelDestinations: List<TopLevelDestinations> = TopLevelDestinations.entries
    val size = topLevelDestinations.size

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestinations){
        trace("Navigation: ${topLevelDestination.name}"){
            val topLevelOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true

                restoreState = true
            }
            when(topLevelDestination){
                TopLevelDestinations.ACTORS -> navController.navigateToActors(topLevelOptions)
                TopLevelDestinations.MOVIES -> navController.navigateToMovies(topLevelOptions)
                TopLevelDestinations.TVSHOWS -> navController.navigateToTvShows(topLevelOptions)
                TopLevelDestinations.MYLIST -> navController.navigateToMyList(topLevelOptions)
            }
        }
    }

    //fun navigateToSearch() = navController.navigateToSearch()
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}




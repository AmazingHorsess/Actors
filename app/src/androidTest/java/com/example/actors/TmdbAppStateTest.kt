package com.example.actors

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import com.example.actors.navigation.ACTORS_ROUTE
import com.example.actors.navigation.MOVIES_ROUTE
import com.example.actors.navigation.MY_LIST_ROUTE
import com.example.actors.navigation.TV_SHOWS_ROUTE
import com.example.actors.navigation.TmdbAppState
import com.example.actors.navigation.rememberTmdbAppState
import com.example.core.testing.testing.util.TestNetworkMonitor
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class TmdbAppStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var state: TmdbAppState

    private val networkMonitor = TestNetworkMonitor()



    @Test
    fun tmdb_currentDestination() = runTest {
        var currentDestination: String? = null

        composeTestRule.setContent {
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberTestNavController()
            state = remember( navController) {
                TmdbAppState(
                    navController = navController,
                    coroutineScope = coroutineScope,
                    networkMonitor = networkMonitor,


                )

            }
            currentDestination = state.currentDestination?.route
            LaunchedEffect(Unit) {
                navController.setCurrentDestination("b")
                
            }
        }
        assertEquals("b", currentDestination)
    }
    @Test
    fun tmdbAppState_destinations() = runTest {
        composeTestRule.setContent {
            state = rememberTmdbAppState(
                networkMonitor = networkMonitor
            )
            val test = state.topLevelDestinations[0]
            Log.d("MyTag", test.toString())
        }
        assertEquals(4,state.topLevelDestinations.size)
        assertTrue(state.topLevelDestinations[0].name.contains("ACTORS", true))
        assertTrue(state.topLevelDestinations[1].name.contains("MOVIES", true))
        assertTrue(state.topLevelDestinations[2].name.contains("TVSHOWS", true))
        assertTrue(state.topLevelDestinations[3].name.contains("MYLIST", true))


    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun tmdbAppState_whenNetworkMonitorIsOffline_StateIsOffline() = runTest(UnconfinedTestDispatcher()) {
        composeTestRule.setContent {
            state = TmdbAppState(
                navController = NavHostController(LocalContext.current),
                coroutineScope = backgroundScope,
                networkMonitor = networkMonitor,
            )
        }

        backgroundScope.launch { state.isOffline.collect() }
        networkMonitor.setConnected(false)
        assertEquals(
            true,
            state.isOffline.value,
        )
    }

}

@Composable
private fun rememberTestNavController(): TestNavHostController {
    val context = LocalContext.current
    return remember {
        TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            graph = createGraph(startDestination = "a") {
                composable("a") { }
                composable("b") { }
                composable("c") { }
            }
        }
    }
}
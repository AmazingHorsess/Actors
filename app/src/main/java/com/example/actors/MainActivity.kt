package com.example.actors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.example.actors.navigation.TmdbApp
import com.example.actors.navigation.rememberTmdbAppState
import com.example.actors.ui.navigation.AppNavigation
import com.example.actors.ui.theme.TmdbTheme
import core.data.util.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(

        )
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)


        setContent {
            TmdbTheme {
                val appState = rememberTmdbAppState(
                    networkMonitor = networkMonitor,
                )


                TmdbApp(appState = appState)
                // A surface container using the 'background' color from the theme

            }
        }
    }
}


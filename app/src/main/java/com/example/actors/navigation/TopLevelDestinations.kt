package com.example.actors.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.actors.R

enum class TopLevelDestinations(
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val titleTextId: Int,

) {
    ACTORS(
        selectedIcon = Icons.Default.AccountCircle,
        unselectedIcon = Icons.Default.AccountCircle,
        titleTextId = R.string.feature_actors
    ),
    MOVIES(
    selectedIcon = Icons.Default.AccountCircle,
    unselectedIcon = Icons.Default.AccountCircle,
        titleTextId = R.string.feature_movies
    ),
    TVSHOWS(
    selectedIcon = Icons.Default.AccountCircle,
    unselectedIcon = Icons.Default.AccountCircle,
        titleTextId = R.string.feature_tvshows
    ),
    MYLIST(
    selectedIcon = Icons.Default.AccountCircle,
    unselectedIcon = Icons.Default.AccountCircle,
        titleTextId = R.string.feature_mylist
    )


}
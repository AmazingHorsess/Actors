package com.example.actors.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.screens.search.SearchType
import com.example.actors.ui.theme.TmdbTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    navigateToSearch: (SearchType) -> Unit,
    searchType: SearchType
) {

    TopAppBar(
        title = {},
        navigationIcon = {HomeTopAppBarContent(navigateToSearch, searchType)},
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor =MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        ),

        modifier = modifier
            .statusBarsPadding()
            .padding(top = 4.dp, start = 16.dp,end= 16.dp)




            

    )
}

/**
 * AppBar for [HomeScreen]
 */
@Composable
private fun HomeTopAppBarContent(
    navigateToSearch: (SearchType) -> Unit,
    searchType: SearchType
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable { navigateToSearch(searchType) }
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.cd_search_icon),
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.alpha(0.5f)
        )

        Spacer(modifier = Modifier.padding(horizontal = 12.dp))

        Text(
            text = stringResource(R.string.search_app_bar_title),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,

            modifier = Modifier.alpha(0.5f)
        )
    }
}

@Preview
@Composable
private fun SearchBarLightPreview() {
    TmdbTheme(darkTheme = true) {
        HomeTopAppBarContent(
            navigateToSearch = { },
            searchType = SearchType.ACTORS
        )
    }
}
@Preview
@Composable
private fun SearchBarDarkPreview() {
    TmdbTheme(darkTheme = false) {
        HomeTopAppBarContent(
            navigateToSearch = { },
            searchType = SearchType.ACTORS
        )
    }
}
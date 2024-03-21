package com.example.actors.ui.screens.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.theme.TmdbTheme

@Composable
fun MovieDetailTopAppBar(
    modifier: Modifier,
    navigateUp: () -> Unit,
    title: String?,
    showTopBarBackground: State<Boolean>
){

    val conditionalModifier = when{
        showTopBarBackground.value -> modifier.background(color = MaterialTheme.colorScheme.background)
        else -> modifier

    }
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = conditionalModifier
            .fillMaxWidth()
    ){
        IconButton(
            onClick = navigateUp,
            modifier = Modifier.padding(start = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack ,
                contentDescription = stringResource(id = R.string.cd_up_button),
                tint = MaterialTheme.colorScheme.onBackground
            )


        }
        Text(
            text = "$title",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp)
        )

    }
}
@Composable
@Preview(showBackground = true)
fun MovieDetailTopAppBarConditionalTrueLight(title:String = "test"){
    val showTopBarBackground = remember { mutableStateOf(true) }
    TmdbTheme(darkTheme = false) {
        MovieDetailTopAppBar(
            modifier = Modifier,
            navigateUp = {},
            title = title,
            showTopBarBackground = showTopBarBackground
        )

    }
}
@Composable
@Preview(showBackground = true)
fun MovieDetailTopAppBarConditionalTrueBlack(title:String = "test"){
    val showTopBarBackground = remember { mutableStateOf(true) }
    TmdbTheme(darkTheme = true) {
        MovieDetailTopAppBar(
            modifier = Modifier,
            navigateUp = {},
            title = title,
            showTopBarBackground = showTopBarBackground
        )

    }
}
@Composable
@Preview(showBackground = true)
fun MovieDetailTopAppBarConditionalFalseLight(){
    val showTopBarBackground = remember { mutableStateOf(true) }
    TmdbTheme(darkTheme = false) {
        MovieDetailTopAppBar(
            modifier = Modifier,
            navigateUp = {},
            title = "MovieTest",
            showTopBarBackground = showTopBarBackground
        )

    }
}
@Composable
@Preview(showBackground = true)
fun MovieDetailTopAppBarConditionalFalseDark(){
    val showTopBarBackground = remember { mutableStateOf(true) }
    TmdbTheme(darkTheme = true) {
        MovieDetailTopAppBar(
            modifier = Modifier,
            navigateUp = {},
            title = "MovieTest",
            showTopBarBackground = showTopBarBackground
        )

    }
}



package com.example.actors.ui.screens.search

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.components.AppDivider
import com.example.actors.ui.components.KeyboardState
import com.example.actors.ui.components.closeKeyboardAndNavigateUp
import com.example.actors.ui.components.getCurrentKeyboardState
import com.example.actors.ui.theme.TmdbTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    navigateUp: () -> Unit,
    onQueryChange: (searchQuery: String) -> Unit,
    searchHint: String,
    closeKeyboard: () -> Unit?

){
    // Immediately update and keep track of query from text field changes.
    var query: String by rememberSaveable { mutableStateOf("") }

    // Handle clear icon state whether to show or hide based on query.
    var showClearQueryIcon: Boolean by rememberSaveable { mutableStateOf(false) }
    showClearQueryIcon = when{
        query.isEmpty() -> false
        query.isNotEmpty() -> true
        else -> false
    }
    // Detects whether a current keyboard is opened or closed
    val keyboardState: KeyboardState by getCurrentKeyboardState()
    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult() ,
    ){ result ->
        val recordedSpeech = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        if (!recordedSpeech.isNullOrEmpty()) {
            query = recordedSpeech[0]
            // Perform query with the recorded query string.
            onQueryChange(query)
        }


    }
    Column {
        Spacer(modifier = Modifier.statusBarsPadding())

        TextField(
            value = query,
            onValueChange = { newQuery ->
                // If user makes changes to text, immediately updated it.
                query = newQuery
                // To avoid crash, only query when string isn't empty.
                if (newQuery.isNotEmpty()) {
                    // Pass latest query to refresh search results.
                    onQueryChange(newQuery)
                }
            },
            leadingIcon = {
                IconButton(
                    modifier = Modifier.padding(start = 4.dp),
                    onClick = {
                        closeKeyboardAndNavigateUp(
                            navigateUp = navigateUp,
                            closeKeyboard = closeKeyboard,
                            keyboardState = keyboardState
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = stringResource(id = R.string.cd_search_icon)
                    )
                }
            },
            trailingIcon = {
                if (showClearQueryIcon) {
                    IconButton(
                        onClick = {
                            query = ""
                            closeKeyboard()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = stringResource(id = R.string.cd_clear_icon)
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            // This starts the activity and populates the intent with the speech text.
                            resultLauncher.launch(createLaunchSpeechRecognitionIntent)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mic),
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "",
                        )
                    }
                }
            },
            placeholder = { Text(text = searchHint) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background, RectangleShape),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent

            )
        )
        AppDivider(verticalPadding = 0.dp)
    }

}
private val createLaunchSpeechRecognitionIntent = Intent(
    RecognizerIntent.ACTION_RECOGNIZE_SPEECH
).apply {
    putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
}
@Preview
@Composable
private fun SearchAppBarLightPreview() {
    TmdbTheme(darkTheme = true) {
        SearchAppBar(
            navigateUp = { },
            onQueryChange = { },
            searchHint = stringResource(id = R.string.hint_search_query_actors),
            closeKeyboard = { }
        )
    }
}

@Preview
@Composable
private fun SearchAppBarDarkPreview() {
    TmdbTheme(darkTheme = false) {
        SearchAppBar(
            navigateUp = { },
            onQueryChange = { },
            searchHint = stringResource(id = R.string.hint_search_query_movies),
            closeKeyboard = { }
        )
    }
}
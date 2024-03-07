package com.example.actors.ui.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetState
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomBar(
    modalSheetSheet: ModalBottomSheetState,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    BottomAppBar(
        containerColor = (MaterialTheme.colorScheme.surface),

        tonalElevation = 0.dp,
        contentPadding = paddingValues,
        modifier = Modifier
            .clip(RoundedCornerShape(topStartPercent = 25, topEndPercent = 25))
            .background(MaterialTheme.colorScheme.surface)
            .navigationBarsPadding()
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            modalSheetSheet.expand()
                        }
                    },
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(28.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewHomeBottomBar() {
    HomeBottomBar(
        modalSheetSheet = com.example.actors.ui.components.ModalBottomSheetLayout.rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.HalfExpanded,
            animationSpec = tween(durationMillis = 500),

        )
    )
}
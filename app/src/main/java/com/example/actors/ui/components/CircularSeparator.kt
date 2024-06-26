package com.example.actors.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularSeparator(
    horPadding: Dp = 12.dp,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = horPadding)
            .size(6.dp)
            .clip(CircleShape)
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
                shape = CircleShape
            )
    )
}
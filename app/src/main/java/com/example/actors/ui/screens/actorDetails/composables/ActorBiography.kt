package com.example.actors.ui.screens.actorDetails.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actors.R
import com.example.actors.ui.components.CategoryTitle
import com.example.actors.ui.components.verticalGradientScrim

@Composable
internal fun ActorBiography(
    biography: String?
) {
    Column(
        modifier = Modifier
            .verticalGradientScrim(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f),
                startYPercentage = 0f,
                endYPercentage = 1f
            )
            .padding(
                bottom = 112.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_biography),
                contentDescription = stringResource(id = R.string.cd_biography_icon),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                alpha = 0.5f,
                modifier = Modifier.size(36.dp),
            )

            CategoryTitle(
                title = stringResource(R.string.cast_biography_title)
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        // Make use of style to modify line height for the paragraph.
        if (biography != null) {
            Text(
                text = biography,
                style = TextStyle(
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify
                )
            )
        }
    }
}
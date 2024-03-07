package com.example.actors.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.room.util.appendPlaceholders
import coil.compose.rememberImagePainter
import com.example.actors.R

@Composable
fun LoadNetworkImage(
    imageUrl: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    shape: Shape,
    showAnimProgress: Boolean = true,
) {
    Image(painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            if (showAnimProgress) {
                placeholder(R.drawable.animated_progress)
                error(R.drawable.ic_image_not_available)

            }
        }),
        contentDescription = contentDescription,
        contentScale =  ContentScale.Crop,
        modifier = modifier
            .clip(shape)
            .background(color = MaterialTheme.colorScheme.surface)
    )


}

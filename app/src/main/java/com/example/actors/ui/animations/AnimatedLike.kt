package com.example.actors.ui.animations

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedLikeIcon(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    onClick: () -> Unit
) {
    val iconColor = remember { Animatable(Color.Gray) }
    val yOffset = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(isLiked) {
        if (isLiked) {
            iconColor.animateTo(Color.Red)
        } else {
            iconColor.animateTo(Color.Gray)
        }
    }

    LaunchedEffect(isLiked) {
        if (isLiked) {
            yOffset.animateTo(-40f)
            yOffset.animateTo(40f)
            yOffset.animateTo(-30f)
            yOffset.animateTo(30f)
            yOffset.animateTo(-20f)
            yOffset.animateTo(20f)
            yOffset.animateTo(-10f)
            yOffset.animateTo(10f)
            yOffset.animateTo(0f)
        }
    }

    IconButton(
        modifier = modifier.offset(y = yOffset.value.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Like Icon",
            tint = iconColor.value
        )
    }
}
@Preview
@Composable
fun LikePreview(){
    var liked = false
    AnimatedLikeIcon(

        isLiked = liked
    ) { liked = !liked }


}


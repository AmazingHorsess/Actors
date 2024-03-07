package com.example.actors.ui.animations


import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.example.actors.ui.theme.ActorsTheme


@Composable
private fun AnimateShapeInfinietely(
    animateShape: androidx.compose.animation.core.Animatable<Float,AnimationVector1D>,
    targetValue: Float = 1f,
    durationMillis: Int = 1000
) {
    LaunchedEffect(animateShape){
        animateShape.animateTo(
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
    }
}
@Composable
fun AnimatedSearch(){
    val animateCircle = remember {androidx.compose.animation.core.Animatable(0.1f)}.apply {
        AnimateShapeInfinietely(this)
    }

    val animateLine = remember { androidx.compose.animation.core.Animatable(0.6f)}.apply {
        AnimateShapeInfinietely(this)
    }

    val surfaceColor = MaterialTheme.colorScheme.surface
    Canvas(
        modifier = Modifier,
        ){
        drawArc(
            color = surfaceColor,
            startAngle = 45f,
            sweepAngle = 360f * animateCircle.value,
            useCenter = false,
            size = Size(80f,80f),
            style = Stroke(16f,cap = StrokeCap.Round)
        )
        drawLine(
            color = surfaceColor,
            strokeWidth = 16f,
            cap = StrokeCap.Round,
            start = Offset(
                animateLine.value * 80f,
                animateLine.value * 80f

            ),
            end = Offset(
                animateLine.value * 110f,
                animateLine.value * 100f
            )
        )
    }
}

@Composable
@Preview
fun AnimateSearchPreview(){
    ActorsTheme {
        AnimatedSearch()

    }

}
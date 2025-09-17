package com.aziz.jokebook.ui.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun CoverScreen(onEnter: () -> Unit) {
    // Animated “aurora” background using theme colors
    val colors = MaterialTheme.colorScheme
    val infinite = rememberInfiniteTransition(label = "bg")
    val c1 by infinite.animateColor(
        initialValue = colors.primaryContainer,
        targetValue = colors.secondaryContainer,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "c1"
    )
    val c2 by infinite.animateColor(
        initialValue = colors.tertiaryContainer,
        targetValue = colors.primary.copy(alpha = 0.6f),
        animationSpec = infiniteRepeatable(
            animation = tween(7000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "c2"
    )
    val c3 by infinite.animateColor(
        initialValue = colors.secondary.copy(alpha = 0.4f),
        targetValue = colors.tertiary.copy(alpha = 0.4f),
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "c3"
    )

    val bgBrush = Brush.verticalGradient(listOf(c1, c2, c3))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgBrush)
            .padding(24.dp)
    ) {
        // Glass card hero
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(10.dp, shape = RoundedCornerShape(28.dp), clip = false),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f),
            tonalElevation = 2.dp
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Joke Book",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "A light-weight app to brighten your day.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(28.dp))

                GradientPillButton(
                    text = "Open Joke Book",
                    onClick = onEnter,
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .height(52.dp)
                )
            }
        }

        // Optional subtle credit/footer (comment out if you prefer super minimal)
        // Text(
        //     "Powered by Jetpack Compose",
        //     modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
        //     style = MaterialTheme.typography.labelSmall,
        //     color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        // )
    }
}

/**
 * A modern pill button with animated gradient & press scale.
 * No extra dependencies; uses Material 3 and Compose only.
 */
@Composable
private fun GradientPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(28.dp)
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale = if (pressed) 0.98f else 1f

    // Use theme colors so it looks great in light/dark & dynamic color
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.tertiary
        )
    )

    Button(
        onClick = onClick,
        interactionSource = interaction,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        modifier = modifier
            .graphicsLayer {
                this.scaleX = scale
                this.scaleY = scale
            }
            .clip(shape)
            .background(gradient, shape)
            .shadow(16.dp, shape = shape, clip = false)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

package com.example.moviesmobile.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun AnimatedHeartButton(
    modifier: Modifier = Modifier,
    tint: Color,
    onHeartClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.3f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        )
    )

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(350)
            isPressed = false
        }
    }

    IconButton(
        onClick = { 
            isPressed = true
            onHeartClick()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Add to favorites",
            tint = tint,
            modifier = Modifier.scale(scale)
        )
    }
} 
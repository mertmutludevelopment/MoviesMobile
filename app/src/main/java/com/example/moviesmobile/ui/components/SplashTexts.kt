package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashTexts(alphaAnim: Float) {
    Text(
        text = "CineSpot",
        color = Color.White,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.alpha(alphaAnim)
    )
    
    Spacer(modifier = Modifier.height(8.dp))
    
    Text(
        text = "Your Ultimate Movie Experience",
        color = Color.White.copy(alpha = 0.7f),
        fontSize = 16.sp,
        modifier = Modifier.alpha(alphaAnim)
    )
} 
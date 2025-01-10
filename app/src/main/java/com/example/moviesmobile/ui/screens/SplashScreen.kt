package com.example.moviesmobile.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.SplashAnimation
import com.example.moviesmobile.ui.components.SplashTexts
import kotlinx.coroutines.delay

// Initial loading screen with animated logo and text
// Automatically navigates to main screen after animation completes
@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }

    // Fade-in animation for text elements
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500
        )
    )

    // Start animation and navigate to main screen after delay
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navController.navigate("mainScreen") {
            popUpTo("splashScreen") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SplashAnimation()
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SplashTexts(alphaAnim = alphaAnim.value)
        }
    }
} 
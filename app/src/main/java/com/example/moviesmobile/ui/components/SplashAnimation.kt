package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.moviesmobile.R

@Composable
fun SplashAnimation() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.movie_clapper)
    )

    val lottieAnimatable by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        isPlaying = true
    )

    LottieAnimation(
        composition = composition,
        progress = { lottieAnimatable },
        modifier = Modifier.size(200.dp)
    )
} 
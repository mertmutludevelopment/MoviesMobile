package com.example.moviesmobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailTopBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { navController.popBackStack() },
        modifier = modifier
            .padding(16.dp)
            .background(Color.Black.copy(alpha = 0.25f), shape = RoundedCornerShape(25.dp))
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
        )
    }
} 
package com.example.moviesmobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.theme.OnPrimary
import com.example.moviesmobile.ui.theme.Primary

@Composable
fun DetailTopBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.25f), shape = RoundedCornerShape(25.dp))
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        IconButton(
            onClick = { /* TODO: Favoriye ekleme işlemi */ },
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.25f), shape = RoundedCornerShape(25.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Add to favorites",
                tint = OnPrimary
            )
        }
    }
} 
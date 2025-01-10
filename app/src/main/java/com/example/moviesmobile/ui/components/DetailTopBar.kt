package com.example.moviesmobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.theme.Primary
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel

@Composable
fun DetailTopBar(
    navController: NavController,
    movie: Movie,
    favoriteViewModel: FavoriteScreenViewModel,
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
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        AnimatedHeartButton(
            modifier = Modifier,
            tint = Primary,
            isFavorite = favoriteViewModel.isFavorite(movie),
            onHeartClick = { favoriteViewModel.toggleFavorite(movie) }
        )
    }
} 
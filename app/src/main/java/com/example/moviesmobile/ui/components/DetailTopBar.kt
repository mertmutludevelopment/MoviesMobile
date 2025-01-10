package com.example.moviesmobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.theme.Primary
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailTopBar(
    navController: NavController,
    movie: Movie,
    favoriteViewModel: FavoriteScreenViewModel,
    modifier: Modifier = Modifier,
    alpha: Float = 1f
) {
    val coroutineScope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }

    // İlk yüklemede favori durumunu kontrol et
    LaunchedEffect(movie.id) {
        isFavorite = favoriteViewModel.isFavorite(movie)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.graphicsLayer(alpha = alpha)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        AnimatedHeartButton(
            modifier = Modifier.graphicsLayer(alpha = alpha),
            tint = Primary,
            isFavorite = isFavorite,
            onHeartClick = {
                coroutineScope.launch {
                    favoriteViewModel.toggleFavorite(movie)
                    isFavorite = !isFavorite
                }
            }
        )
    }
} 
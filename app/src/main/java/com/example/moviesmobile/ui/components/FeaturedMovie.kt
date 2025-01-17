package com.example.moviesmobile.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.theme.OnPrimary
import com.example.moviesmobile.ui.theme.Primary
import com.example.moviesmobile.ui.theme.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel

// Featured movie card with parallax scrolling effect and favorite functionality
@Composable
fun FeaturedMovie(
    movie: Movie,
    onMovieClick: (Int) -> Unit,
    scrollOffset: Float = 0f,
    favoriteViewModel: FavoriteScreenViewModel
) {
    var isFavorite by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(movie.id) {
        isFavorite = favoriteViewModel.isFavorite(movie)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationY = scrollOffset * 0.5f // Parallax effect
                },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Surface),
            onClick = { onMovieClick(movie.id) }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = "${AppConstants.IMAGE_URL}${movie.image}",
                    contentDescription = movie.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            translationY = -scrollOffset * 0.2f // Slight opposite movement for depth
                        },
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = 0.5f
                        }
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                colors = listOf(
                                    androidx.compose.ui.graphics.Color.Transparent,
                                    androidx.compose.ui.graphics.Color.Black
                                ),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Primary),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "Featured",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = OnPrimary,
                            fontSize = 12.sp
                        )
                    }

                    Column {
                        Text(
                            text = movie.name,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnPrimary
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "★ ${movie.rating}",
                                color = Primary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "•",
                                color = Surface,
                                fontSize = 18.sp
                            )
                            Text(
                                text = movie.year.toString(),
                                color = OnPrimary,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

                AnimatedHeartButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
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
    }
}
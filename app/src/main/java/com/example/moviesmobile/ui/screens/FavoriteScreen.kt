package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.components.AnimatedHeartButton
import com.example.moviesmobile.ui.theme.*
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteScreenViewModel
) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Top Bar
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

            Text(
                text = "Favorites",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Boş box ile dengeleme
            Box(modifier = Modifier.size(48.dp))
        }

        // Favorite Movies List
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoriteMovies) { movie ->
                FavoriteMovieCard(
                    movie = movie,
                    onMovieClick = { navController.navigate("detailScreen/${movie.id}") },
                    onRemoveClick = { viewModel.removeFromFavorites(movie) }
                )
            }
        }
    }
}

@Composable
private fun FavoriteMovieCard(
    movie: Movie,
    onMovieClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        onClick = onMovieClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            // Movie Poster
            AsyncImage(
                model = "${AppConstants.IMAGE_URL}${movie.image}",
                contentDescription = movie.name,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Movie Info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = movie.name,
                        color = OnSurface,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "★ ${movie.rating}",
                        color = Primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${movie.year} • ${movie.category}",
                        color = OnSurface.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                }

                // Remove from favorites button
                AnimatedHeartButton(
                    modifier = Modifier.align(Alignment.End),
                    tint = Primary,
                    onHeartClick = onRemoveClick
                )
            }
        }
    }
}


package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.theme.OnSurface
import com.example.moviesmobile.ui.theme.Primary
import com.example.moviesmobile.ui.theme.Surface
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun TopRatedMovies(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteScreenViewModel
) {
    var favoriteStates by remember { mutableStateOf(mapOf<Int, Boolean>()) }
    val coroutineScope = rememberCoroutineScope()

    // İlk yüklemede tüm filmlerin favori durumunu kontrol et
    LaunchedEffect(movies) {
        val states = mutableMapOf<Int, Boolean>()
        movies.forEach { movie ->
            states[movie.id] = favoriteViewModel.isFavorite(movie)
        }
        favoriteStates = states
    }

    Column(modifier = modifier) {
        Text(
            text = "Top Rated",
            color = OnSurface,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(movies) { movie ->
                Card(
                    modifier = Modifier.width(150.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    onClick = { onMovieClick(movie.id) }
                ) {
                    Box {
                        Column {
                            AsyncImage(
                                model = "${AppConstants.IMAGE_URL}${movie.image}",
                                contentDescription = movie.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = movie.name,
                                    color = OnSurface,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )

                                Spacer(modifier = Modifier.height(4.dp))
                                
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = movie.year.toString(),
                                        color = OnSurface.copy(alpha = 0.7f),
                                        fontSize = 14.sp
                                    )

                                    Text(
                                        text = "★ ${movie.rating}",
                                        color = Primary,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        AnimatedHeartButton(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .size(28.dp),
                            tint = Primary,
                            isFavorite = favoriteStates[movie.id] ?: false,
                            onHeartClick = {
                                coroutineScope.launch {
                                    favoriteViewModel.toggleFavorite(movie)
                                    favoriteStates = favoriteStates.toMutableMap().apply {
                                        put(movie.id, !(favoriteStates[movie.id] ?: false))
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
} 
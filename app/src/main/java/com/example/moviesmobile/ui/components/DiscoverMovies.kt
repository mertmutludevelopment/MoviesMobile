package com.example.moviesmobile.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.constants.MovieDurations
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.theme.*

@Composable
fun CategoryChip(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Surface),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                color = OnSurface,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DiscoverMovies(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Discover Movies",
            color = OnSurface,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        movies.forEach { movie ->
            var expanded by remember { mutableStateOf(false) }
            
            val rotationState by animateFloatAsState(
                targetValue = if (expanded) 180f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Surface)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = "${AppConstants.IMAGE_URL}${movie.image}",
                            contentDescription = movie.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        // Gradient overlay
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f)
                                        ),
                                        startY = 0f,
                                        endY = Float.POSITIVE_INFINITY
                                    )
                                )
                        )

                        // Movie info on top of the image
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = movie.name,
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = movie.year.toString(),
                                    color = Color.White.copy(alpha = 0.7f),
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

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Kalp ikonu
                            IconButton(
                                onClick = { /* TODO: Favoriye ekleme işlemi */ }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Add to favorites",
                                    tint = Primary
                                )
                            }

                            // Genişletme/Daraltma ikonu
                            IconButton(
                                onClick = { expanded = !expanded }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = if (expanded) "Show less" else "Show more",
                                    tint = Color.White,
                                    modifier = Modifier.rotate(rotationState)
                                )
                            }
                        }
                    }

                    if (expanded) {
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                CategoryChip(
                                    icon = Icons.Default.LocalMovies,
                                    text = movie.category ?: "Action"
                                )

                                CategoryChip(
                                    icon = Icons.Default.Timer,
                                    text = "${MovieDurations.getDuration(movie.name)} min"
                                )

                                CategoryChip(
                                    icon = Icons.Default.Language,
                                    text = "EN"
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "A gripping sci-fi mystery where a convict is sent back in time to stop a deadly virus that wiped out humanity. But uncovering the truth in the past proves more challenging than expected.",
                                color = OnSurface.copy(alpha = 0.7f),
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { onMovieClick(movie.id) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = Primary)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = OnPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Watch Now",
                                    color = OnPrimary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
} 
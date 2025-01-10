package com.example.moviesmobile.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.theme.OnSurface
import com.example.moviesmobile.ui.theme.Primary
import androidx.compose.ui.graphics.Color

@Composable
fun DiscoverMovieCard(
    movie: Movie,
    onMovieClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onMovieClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box {
                AsyncImage(
                    model = "${AppConstants.IMAGE_URL}${movie.image}",
                    contentDescription = movie.name,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { /* TODO: Favoriye ekleme işlemi */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Add to favorites",
                        tint = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = movie.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Rating: ${movie.rating}",
                    fontSize = 16.sp,
                    color = Primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (isExpanded) {
                    Text(
                        text = "Year: ${movie.year}",
                        fontSize = 16.sp,
                        color = OnSurface
                    )
                    Text(
                        text = "Category: ${movie.category}",
                        fontSize = 16.sp,
                        color = OnSurface
                    )
                }

                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (isExpanded) "Show less" else "Show more",
                        tint = OnSurface
                    )
                }
            }
        }
    }
} 
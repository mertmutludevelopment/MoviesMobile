package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesmobile.ui.theme.*
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel
import androidx.compose.foundation.clickable

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel
) {
    val movies by mainScreenViewModel.movies.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { 
                        navController.navigate("detailScreen/${movie.id}")
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    AsyncImage(
                        model = "http://kasimadalan.pe.hu/movies/images/${movie.image}",
                        contentDescription = movie.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = movie.name,
                            color = OnSurface,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        
                        Text(
                            text = "Yıl: ${movie.year}",
                            color = OnSurface.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                        
                        Text(
                            text = "Yönetmen: ${movie.director ?: "Belirtilmemiş"}",
                            color = OnSurface.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                        
                        movie.rating?.let {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Puan: $it",
                                color = Primary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}


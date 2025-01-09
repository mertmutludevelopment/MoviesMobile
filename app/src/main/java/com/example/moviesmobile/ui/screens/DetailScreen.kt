package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesmobile.ui.theme.*
import com.example.moviesmobile.ui.viewmodel.DetailScreenViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.moviesmobile.ui.components.OrderAmountDialog
import com.example.moviesmobile.constants.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailScreenViewModel,
    movieId: Int
) {
    val movie by viewModel.movie.collectAsState()
    val description by viewModel.description.collectAsState()
    var showOrderDialog by remember { mutableStateOf(false) }
    var selectedAmount by remember { mutableStateOf(1) }

    OrderAmountDialog(
        showDialog = showOrderDialog,
        selectedAmount = selectedAmount,
        onAmountChange = { selectedAmount = it },
        onConfirm = {
            viewModel.addToCart(selectedAmount)
            showOrderDialog = false
        },
        onDismiss = { showOrderDialog = false }
    )

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        TopAppBar(
            title = { Text(text = movie?.name ?: "", color = OnSurface) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Geri",
                        tint = OnSurface
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Surface
            )
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(bottom = 80.dp)
            ) {
                AsyncImage(
                    model = "${AppConstants.IMAGE_URL}${movie?.image}",
                    contentDescription = movie?.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = movie?.name ?: "",
                    color = OnSurface,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Yıl: ${movie?.year}",
                        color = OnSurface.copy(alpha = 0.7f),
                        fontSize = 16.sp
                    )
                    
                    movie?.rating?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = it.toString(),
                                color = Primary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Yönetmen: ${movie?.director ?: "Belirtilmemiş"}",
                    color = OnSurface.copy(alpha = 0.7f),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Film Açıklaması",
                    color = OnSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = description,
                    color = OnSurface.copy(alpha = 0.7f),
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }

            Button(
                onClick = { showOrderDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = OnPrimary
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Satın Al",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


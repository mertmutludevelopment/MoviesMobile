package com.example.moviesmobile.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.moviesmobile.ui.theme.OnSurface
import com.example.moviesmobile.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(
    title: String,
    navController: NavController
) {
    TopAppBar(
        title = { Text(text = title, color = OnSurface) },
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
} 
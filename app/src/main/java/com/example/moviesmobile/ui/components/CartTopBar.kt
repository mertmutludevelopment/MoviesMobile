package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.theme.OnSurface
import com.example.moviesmobile.ui.theme.Surface

// Top app bar component for shopping cart screen with back navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Shopping Cart",
                color = OnSurface
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { 
                    navController.popBackStack()
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = OnSurface
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Surface
        )
    )
} 
package com.example.moviesmobile.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel

@Composable
fun ScreenNavigation(
    mainScreenViewModel: MainScreenViewModel,
    ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(
                navController = navController,
                mainScreenViewModel = mainScreenViewModel
            )
        }
    }
}







package com.example.moviesmobile.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesmobile.ui.viewmodel.CartScreenViewModel
import com.example.moviesmobile.ui.viewmodel.DetailScreenViewModel
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel

@Composable
fun ScreenNavigation(
    mainScreenViewModel: MainScreenViewModel,
    detailScreenViewModel: DetailScreenViewModel,
    cartScreenViewModel: CartScreenViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashScreen") {
        composable("splashScreen") {
            SplashScreen(navController = navController)
        }

        composable("mainScreen") {
            MainScreen(
                navController = navController,
                mainScreenViewModel = mainScreenViewModel
            )
        }

        composable(
            route = "detailScreen/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            DetailScreen(
                navController = navController,
                viewModel = detailScreenViewModel,
                movieId = movieId
            )
        }

        composable("cartScreen") {
            CartScreen(
                navController = navController,
                viewModel = cartScreenViewModel
            )
        }
    }
}







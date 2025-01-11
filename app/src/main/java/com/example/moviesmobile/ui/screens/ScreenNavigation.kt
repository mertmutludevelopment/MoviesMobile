package com.example.moviesmobile.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesmobile.ui.viewmodel.CartScreenViewModel
import com.example.moviesmobile.ui.viewmodel.DetailScreenViewModel
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel
import com.example.moviesmobile.ui.viewmodel.SignInViewModel

// Main navigation component handling screen transitions and routing
// Manages navigation between Splash, Main, Detail, Cart and Favorite screens
@Composable
fun ScreenNavigation(
    mainScreenViewModel: MainScreenViewModel,
    detailScreenViewModel: DetailScreenViewModel,
    cartScreenViewModel: CartScreenViewModel,
    favoriteScreenViewModel: FavoriteScreenViewModel,
    signInViewModel: SignInViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashScreen") {
        // Splash screen - Initial app loading screen
        composable("splashScreen") {
            SplashScreen(navController = navController)
        }

        // Sign In screen - Yeni eklenen
        composable("signInScreen") {
            SignInScreen(
                navController = navController,
                viewModel = signInViewModel
            )
        }

        // Main screen - Home screen with movie listings
        composable("mainScreen") {
            MainScreen(
                navController = navController,
                mainScreenViewModel = mainScreenViewModel,
                favoriteViewModel = favoriteScreenViewModel
            )
        }

        // Detail screen - Shows movie details with dynamic movieId parameter
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
                favoriteViewModel = favoriteScreenViewModel,
                movieId = movieId
            )
        }

        // Cart screen - Shopping cart with movie tickets
        composable("cartScreen") {
            CartScreen(
                navController = navController,
                viewModel = cartScreenViewModel
            )
        }

        // Favorite screen - User's favorite movies list
        composable("favoriteScreen") {
            FavoriteScreen(
                navController = navController,
                viewModel = favoriteScreenViewModel
            )
        }
    }
}







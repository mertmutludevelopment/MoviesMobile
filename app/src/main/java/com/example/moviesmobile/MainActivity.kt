package com.example.moviesmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.moviesmobile.ui.screens.ScreenNavigation
import com.example.moviesmobile.ui.theme.MoviesMobileTheme
import com.example.moviesmobile.ui.viewmodel.CartScreenViewModel
import com.example.moviesmobile.ui.viewmodel.DetailScreenViewModel
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel
import com.example.moviesmobile.ui.viewmodel.SignInViewModel
import com.example.moviesmobile.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesmobile.ui.screens.SignInScreen
import com.example.moviesmobile.ui.screens.SignUpScreen
import com.example.moviesmobile.data.manager.SessionManager

// Main entry point of the application
// Initializes ViewModels and sets up navigation
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // ViewModels 
    private val mainScreenViewModel: MainScreenViewModel by viewModels()
    private val detailScreenViewModel: DetailScreenViewModel by viewModels()
    private val cartScreenViewModel: CartScreenViewModel by viewModels()
    private val favoriteScreenViewModel: FavoriteScreenViewModel by viewModels()
    private val signInViewModel: SignInViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        enableEdgeToEdge()

        setContent {
            MoviesMobileTheme {
                ScreenNavigation(
                    mainScreenViewModel = mainScreenViewModel,
                    detailScreenViewModel = detailScreenViewModel,
                    cartScreenViewModel = cartScreenViewModel,
                    favoriteScreenViewModel = favoriteScreenViewModel,
                    signInViewModel = signInViewModel,
                    signUpViewModel = signUpViewModel,
                    initialRoute = "splashScreen"
                )
            }
        }
    }
}



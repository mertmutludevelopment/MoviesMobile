package com.example.moviesmobile.ui.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.SignInContent
import com.example.moviesmobile.ui.viewmodel.SignInState
import com.example.moviesmobile.ui.viewmodel.SignInViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme

// Screen composable that handles sign-in logic and state management
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val signInState by viewModel.signInState.collectAsState()

    // Handle SignIn State
    LaunchedEffect(signInState) {
        when (signInState) {
            is SignInState.Success -> {
                navController.navigate("mainScreen") {
                    popUpTo("signInScreen") { inclusive = true }
                }
            }
            is SignInState.Error -> {
                showError = true
                errorMessage = (signInState as SignInState.Error).message
            }
            else -> {}
        }
    }

    Column {
        SignInContent(
            email = email,
            password = password,
            isLoading = isLoading,
            showError = showError,
            errorMessage = errorMessage,
            passwordVisible = passwordVisible,
            onEmailChange = { 
                viewModel.onEmailChange(it)
                showError = false 
            },
            onPasswordChange = { 
                viewModel.onPasswordChange(it)
                showError = false 
            },
            onPasswordVisibilityChange = { passwordVisible = it },
            onSignInClick = { viewModel.onSignInClick() },
            onSignUpClick = { navController.navigate("signUpScreen") }
        )
    }
} 
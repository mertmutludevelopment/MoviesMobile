package com.example.moviesmobile.ui.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.SignInContent
import com.example.moviesmobile.ui.viewmodel.SignInState
import com.example.moviesmobile.ui.viewmodel.SignInViewModel

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
        onSignInClick = { viewModel.onSignInClick() }
    )
} 
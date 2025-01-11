package com.example.moviesmobile.ui.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.SignUpContent
import com.example.moviesmobile.ui.viewmodel.SignUpState
import com.example.moviesmobile.ui.viewmodel.SignUpViewModel
import com.example.moviesmobile.ui.viewmodel.SignInViewModel

// Screen composable that handles user registration and state management
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel,
    signInViewModel: SignInViewModel
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    
    val fullName by viewModel.fullName.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val signUpState by viewModel.signUpState.collectAsState()
    
    //Handle SignUp State
    LaunchedEffect(signUpState) {
        when (signUpState) {
            is SignUpState.Success -> {
                signInViewModel.setCredentials(
                    (signUpState as SignUpState.Success).email,
                    (signUpState as SignUpState.Success).password
                )
                navController.navigate("signInScreen") {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }
            is SignUpState.Error -> {
                showError = true
                errorMessage = (signUpState as SignUpState.Error).message
            }
            else -> {}
        }
    }

    SignUpContent(
        fullName = fullName,
        email = email,
        password = password,
        isLoading = isLoading,
        showError = showError,
        errorMessage = errorMessage,
        passwordVisible = passwordVisible,
        onFullNameChange = { 
            viewModel.onFullNameChange(it)
            showError = false 
        },
        onEmailChange = { 
            viewModel.onEmailChange(it)
            showError = false 
        },
        onPasswordChange = { 
            viewModel.onPasswordChange(it)
            showError = false 
        },
        onPasswordVisibilityChange = { passwordVisible = it },
        onSignUpClick = { viewModel.onSignUpClick() },
        onSignInClick = {
            navController.navigate("signInScreen") {
                popUpTo("signUpScreen") { inclusive = true }
            }
        }
    )
} 
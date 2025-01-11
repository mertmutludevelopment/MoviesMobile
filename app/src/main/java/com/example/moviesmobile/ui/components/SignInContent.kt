package com.example.moviesmobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesmobile.ui.theme.Background

@Composable
fun SignInContent(
    email: String,
    password: String,
    isLoading: Boolean,
    showError: Boolean,
    errorMessage: String,
    passwordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    onSignInClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SignInHeader()
            
            if (showError) {
                ErrorMessage(message = errorMessage)
            }

            EmailInput(
                email = email,
                showError = showError,
                onEmailChange = onEmailChange
            )

            PasswordInput(
                password = password,
                showError = showError,
                passwordVisible = passwordVisible,
                onPasswordChange = onPasswordChange,
                onPasswordVisibilityChange = onPasswordVisibilityChange
            )

            SignInButton(
                isLoading = isLoading,
                enabled = email.isNotEmpty() && password.isNotEmpty() && !isLoading,
                onClick = onSignInClick
            )
        }
    }
} 
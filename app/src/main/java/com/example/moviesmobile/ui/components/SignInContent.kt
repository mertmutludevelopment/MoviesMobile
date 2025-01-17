package com.example.moviesmobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.theme.OnPrimary

// Main content composable for the sign-in screen with form inputs and navigation
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
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
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

            // Input fields for email and password
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
            Spacer(modifier = Modifier.height(16.dp))

            // Sign in button with loading state
            Button(
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !isLoading,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Sign In")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Navigation link to sign up screen
            Text(
                text = "Don't have an account? Sign Up",
                modifier = Modifier.clickable(onClick = onSignUpClick),
                color = OnPrimary
            )
        }
    }
} 
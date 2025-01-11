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

// Main content composable for the sign-up screen with registration form
@Composable
fun SignUpContent(
    fullName: String,
    email: String,
    password: String,
    isLoading: Boolean,
    showError: Boolean,
    errorMessage: String,
    passwordVisible: Boolean,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    onSignUpClick: () -> Unit,
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
            SignUpHeader()
            
            if (showError) {
                ErrorMessage(message = errorMessage)
            }

            // Registration form inputs
            FullNameInput(
                fullName = fullName,
                showError = showError,
                onFullNameChange = onFullNameChange
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
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
            
            // Sign up button with loading state
            Button(
                onClick = onSignUpClick,
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
                    Text("Sign Up")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Navigation link to sign in screen
            Text(
                text = "Already have an account? Sign In",
                modifier = Modifier.clickable(onClick = onSignInClick),
                color = OnPrimary
            )
        }
    }
} 
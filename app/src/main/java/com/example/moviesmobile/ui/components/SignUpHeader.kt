package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesmobile.ui.theme.OnPrimary

// Header component for the sign-up screen with title and spacing
@Composable
fun SignUpHeader() {
    Text(
        text = "Create Account",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = OnPrimary
    )
    Spacer(modifier = Modifier.height(32.dp))
} 
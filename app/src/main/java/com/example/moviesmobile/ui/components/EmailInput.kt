package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.moviesmobile.ui.theme.*

//Reusable email input component with validation and styling
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(
    email: String,
    showError: Boolean,
    onEmailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        isError = showError,
        supportingText = {
            if (showError) {
                Text(text = "Please enter a valid email", color = Error)
            }
        },
        label = { Text("Email", color = OnSurface.copy(alpha = 0.7f)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                tint = Primary
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Primary,
            focusedBorderColor = Primary,
            unfocusedBorderColor = OnSurface.copy(alpha = 0.3f),
            focusedTextColor = OnSurface,
            unfocusedTextColor = OnSurface
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
} 
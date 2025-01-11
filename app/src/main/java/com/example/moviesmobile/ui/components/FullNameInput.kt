package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.theme.OnPrimary
import com.example.moviesmobile.ui.theme.OnSurface
import com.example.moviesmobile.ui.theme.Primary

// Reusable full name input component with validation and styling
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullNameInput(
    fullName: String,
    showError: Boolean,
    onFullNameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = fullName,
        onValueChange = onFullNameChange,
        label = { Text("Full Name", color = OnSurface.copy(alpha = 0.7f)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person Icon",
                tint = if (showError) MaterialTheme.colorScheme.error else Primary
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        isError = showError,
        textStyle = TextStyle(color = OnSurface),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Background,
            unfocusedContainerColor = Background,
            disabledContainerColor = Background,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            unfocusedBorderColor = OnPrimary.copy(alpha = 0.3f),
            focusedLabelColor = OnPrimary,
            unfocusedLabelColor = OnPrimary.copy(alpha = 0.3f)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
} 
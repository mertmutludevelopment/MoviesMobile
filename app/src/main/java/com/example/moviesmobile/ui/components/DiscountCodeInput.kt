package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscountCodeInput(
    onDiscountApplied: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var discountCode by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var isApplied by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = discountCode,
                onValueChange = { 
                    discountCode = it
                    isError = false 
                },
                label = { Text("Discount Code", color = OnSurface.copy(alpha = 0.7f)) },
                enabled = !isApplied,
                isError = isError,
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = OnSurface,
                    unfocusedTextColor = OnSurface,
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = OnSurface.copy(alpha = 0.2f),
                    focusedLabelColor = Primary,
                    unfocusedLabelColor = OnSurface.copy(alpha = 0.7f)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (discountCode == AppConstants.DISCOUNT_CODE) {
                        isApplied = true
                        isError = false
                        onDiscountApplied(true)
                    } else {
                        isError = true
                        onDiscountApplied(false)
                    }
                },
                enabled = !isApplied && discountCode.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = OnPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isApplied) "Discount Applied!" else "Apply Discount",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (isError) {
                Text(
                    text = "Invalid discount code",
                    color = Error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
} 
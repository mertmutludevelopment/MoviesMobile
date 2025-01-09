package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesmobile.ui.theme.*

@Composable
fun OrderAmountDialog(
    showDialog: Boolean,
    selectedAmount: Int,
    onAmountChange: (Int) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Miktar Seçin",
                    color = OnSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (selectedAmount > 1) onAmountChange(selectedAmount - 1) },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Text(
                                text = "-",
                                fontSize = 24.sp,
                                color = Primary
                            )
                        }
                        
                        Text(
                            text = selectedAmount.toString(),
                            fontSize = 24.sp,
                            color = OnSurface,
                            fontWeight = FontWeight.Bold
                        )
                        
                        IconButton(
                            onClick = { if (selectedAmount < 10) onAmountChange(selectedAmount + 1) },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Text(
                                text = "+",
                                fontSize = 24.sp,
                                color = Primary
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary
                    )
                ) {
                    Text("Sepete Ekle")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("İptal", color = Primary)
                }
            },
            containerColor = Surface,
            titleContentColor = OnSurface,
            textContentColor = OnSurface
        )
    }
} 
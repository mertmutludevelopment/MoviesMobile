package com.example.moviesmobile.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.moviesmobile.ui.theme.Primary
import com.example.moviesmobile.ui.theme.OnPrimary

@Composable
fun OrderAmountDialog(
    showDialog: Boolean,
    selectedAmount: Int,
    price: Double,
    onAmountChange: (Int) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    // Dialog görünürlüğünü takip et
    LaunchedEffect(showDialog) {
        isVisible = showDialog
    }

    if (showDialog) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(500)) + scaleIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500)) + scaleOut(animationSpec = tween(500))
        ) {
            Dialog(
                onDismissRequest = {
                    isVisible = false
                    onDismiss()
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Ticket Quantity",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = OnPrimary
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { if (selectedAmount > 1) onAmountChange(selectedAmount - 1) }
                            ) {
                                Text(
                                    text = "-",
                                    fontSize = 24.sp,
                                    color = Primary
                                )
                            }
                            
                            Text(
                                text = selectedAmount.toString(),
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(horizontal = 24.dp),
                                color = OnPrimary
                            )
                            
                            IconButton(
                                onClick = { if (selectedAmount < 10) onAmountChange(selectedAmount + 1) },
                                enabled = selectedAmount < 10
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 24.sp,
                                    color = if (selectedAmount < 10) Primary else Primary.copy(alpha = 0.5f)
                                )
                            }
                        }

                        if (selectedAmount == 10) {
                            Text(
                                text = "Maximum 10 tickets allowed",
                                style = MaterialTheme.typography.bodySmall,
                                color = Primary,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total: ",
                                style = MaterialTheme.typography.titleMedium,
                                color = OnPrimary.copy(alpha = 0.7f)
                            )
                            Text(
                                text = "$${String.format("%.2f", price * selectedAmount)}",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Primary
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = {
                                    isVisible = false
                                    onDismiss()
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Text(
                                    text = "Cancel",
                                    color = Primary
                                )
                            }
                            
                            Button(
                                onClick = {
                                    isVisible = false
                                    onConfirm()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Primary
                                )
                            ) {
                                Text(
                                    text = "Add to Cart",
                                    color = OnPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
} 
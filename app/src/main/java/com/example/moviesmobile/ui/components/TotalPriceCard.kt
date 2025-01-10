package com.example.moviesmobile.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.ui.theme.OnSurface
import com.example.moviesmobile.ui.theme.Primary
import com.example.moviesmobile.ui.theme.Surface

@Composable
fun TotalPriceCard(
    totalPrice: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDiscounted: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total Price:",
                    color = OnSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                
                if (isDiscounted) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "$${String.format("%.2f", totalPrice / (1 - AppConstants.DISCOUNT_PERCENTAGE))}",
                            color = OnSurface.copy(alpha = 0.6f),
                            fontSize = 16.sp,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )
                        Text(
                            text = "$${String.format("%.2f", totalPrice)}",
                            color = Primary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Text(
                        text = "$${String.format("%.2f", totalPrice)}",
                        color = Primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tap to complete your purchase",
                color = OnSurface.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
} 
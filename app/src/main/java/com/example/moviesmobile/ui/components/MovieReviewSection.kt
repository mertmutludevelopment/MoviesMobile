package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.moviesmobile.constants.Review
import com.example.moviesmobile.ui.theme.OnPrimary
import com.example.moviesmobile.ui.theme.Primary

@Composable
fun MovieReviewSection(
    reviews: List<Review>,
    modifier: Modifier = Modifier
) {
    var expandedReviews by remember { mutableStateOf(false) }
    val visibleReviews = if (expandedReviews) reviews else reviews.take(3)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Reviews (${reviews.size})",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = OnPrimary,
            )
            
            TextButton(
                onClick = { expandedReviews = !expandedReviews }
            ) {
                Text(
                    text = if (expandedReviews) "See Less" else "See All",
                    color = Primary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        visibleReviews.forEach { review ->
            ReviewCard(review = review)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
} 
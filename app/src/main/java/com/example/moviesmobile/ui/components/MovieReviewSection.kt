package com.example.moviesmobile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moviesmobile.constants.Review
import com.example.moviesmobile.ui.theme.OnPrimary
import com.example.moviesmobile.ui.theme.Primary
import coil.compose.AsyncImage
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

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

@Composable
private fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Avatar image
                    AsyncImage(
                        model = review.authorAvatar,
                        contentDescription = "Author avatar",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    
                    // Author name
                    Text(
                        text = review.author,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimary
                    )
                }
                
                // Rating
                Text(
                    text = "★ ${review.rating}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary
                )
            }
            
            Text(
                text = review.date,
                style = MaterialTheme.typography.bodyMedium,
                color = OnPrimary.copy(alpha = 0.7f),
                modifier = Modifier.padding(vertical = 4.dp)
            )
            
            Text(
                text = review.content,
                style = MaterialTheme.typography.bodyLarge,
                color = OnPrimary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
} 
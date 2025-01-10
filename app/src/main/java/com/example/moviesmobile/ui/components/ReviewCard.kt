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
import androidx.compose.foundation.shape.CircleShape
import coil.compose.AsyncImage
import com.example.moviesmobile.constants.Review
import com.example.moviesmobile.ui.theme.OnPrimary
import com.example.moviesmobile.ui.theme.Primary

@Composable
fun ReviewCard(
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
                    AsyncImage(
                        model = review.authorAvatar,
                        contentDescription = "Author avatar",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    
                    Text(
                        text = review.author,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimary
                    )
                }
                
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
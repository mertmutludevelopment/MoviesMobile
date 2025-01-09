package com.example.moviesmobile.constants

data class Review(
    val author: String,
    val content: String,
    val rating: Float,
    val date: String,
    val authorAvatar: String
)

object Reviews {
    val movieReviews = listOf(
        Review(
            author = "John Doe",
            content = "This movie was absolutely fantastic! The cinematography was breathtaking and the story kept me engaged throughout. One of the best films I've seen this year.",
            rating = 4.5f,
            date = "March 15, 2024",
            authorAvatar = "https://i.pravatar.cc/150?img=12"
        ),
        Review(
            author = "Sarah Smith",
            content = "Great performances by the entire cast. The direction was superb and the screenplay was well-written. Highly recommended!",
            rating = 4.0f,
            date = "March 10, 2024",
            authorAvatar = "https://i.pravatar.cc/150?img=23"
        ),
        Review(
            author = "Mike Johnson",
            content = "An impressive piece of cinema that combines stunning visuals with a compelling narrative. The sound design deserves special mention.",
            rating = 4.8f,
            date = "March 5, 2024",
            authorAvatar = "https://i.pravatar.cc/150?img=68"
        ),
        Review(
            author = "Emma Wilson",
            content = "The character development was exceptional. Every scene felt meaningful and contributed to the overall story. A masterpiece of modern cinema.",
            rating = 4.7f,
            date = "March 3, 2024",
            authorAvatar = "https://i.pravatar.cc/150?img=5"
        ),
        Review(
            author = "David Chen",
            content = "While the visual effects were stunning, what really stood out was the emotional depth of the story. An unforgettable experience.",
            rating = 4.6f,
            date = "March 1, 2024",
            authorAvatar = "https://i.pravatar.cc/150?img=7"
        )
    )
} 
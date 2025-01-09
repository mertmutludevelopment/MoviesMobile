package com.example.moviesmobile.constants

object MovieDurations {
    fun getDuration(movieName: String): Int {
        return when (movieName) {
            "12 Monkeys" -> 129
            "Batman" -> 152
            "Blade Runner" -> 117
            "Catsby" -> 143
            "Django" -> 165
            "Dune" -> 155
            "Game" -> 128
            "Harry Potter" -> 152
            "Hobbit" -> 169
            "Inception" -> 148
            "Interstellar" -> 169
            "John Wick" -> 101
            "Joker" -> 122
            "Kill Bill" -> 111
            "Prestiege" -> 130
            "Scarface" -> 170
            "Star Wars" -> 121
            "Tenet" -> 150
            "The Hateful Eight" -> 168
            "The Pianist" -> 150
            else -> 120 // Default duration
        }
    }

    // Süreyi formatlamak için yardımcı fonksiyon
    fun formatDuration(minutes: Int): String {
        val hours = minutes / 60
        val mins = minutes % 60
        return if (hours > 0) {
            "${hours}h ${mins}m"
        } else {
            "${mins}m"
        }
    }
} 
package com.example.moviesmobile.constants

data class MovieCrew(
    val director: String,
    val cast: List<String>
)

object MovieCrewData {
    val crewInfo = mapOf(
        "12 Monkeys" to MovieCrew(
            director = "Terry Gilliam",
            cast = listOf("Bruce Willis", "Brad Pitt", "Madeleine Stowe")
        ),
        "Batman" to MovieCrew(
            director = "Tim Burton",
            cast = listOf("Michael Keaton", "Jack Nicholson", "Kim Basinger")
        ),
        "Blade Runner" to MovieCrew(
            director = "Ridley Scott",
            cast = listOf("Harrison Ford", "Rutger Hauer", "Sean Young")
        ),
        "Catsby" to MovieCrew(
            director = "Baz Luhrmann",
            cast = listOf("Leonardo DiCaprio", "Tobey Maguire", "Carey Mulligan")
        ),
        "Django" to MovieCrew(
            director = "Quentin Tarantino",
            cast = listOf("Jamie Foxx", "Christoph Waltz", "Leonardo DiCaprio")
        ),
        "Dune" to MovieCrew(
            director = "Denis Villeneuve",
            cast = listOf("Timothée Chalamet", "Zendaya", "Rebecca Ferguson")
        ),
        "Game" to MovieCrew(
            director = "Mark Neveldine, Brian Taylor",
            cast = listOf("Gerard Butler", "Michael C. Hall", "Amber Valletta")
        ),
        "Harry Potter" to MovieCrew(
            director = "Chris Columbus",
            cast = listOf("Daniel Radcliffe", "Rupert Grint", "Emma Watson")
        ),
        "Hobbit" to MovieCrew(
            director = "Peter Jackson",
            cast = listOf("Martin Freeman", "Ian McKellen", "Richard Armitage")
        ),
        "Inception" to MovieCrew(
            director = "Christopher Nolan",
            cast = listOf("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page")
        ),
        "Interstellar" to MovieCrew(
            director = "Christopher Nolan",
            cast = listOf("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain")
        ),
        "John Wick" to MovieCrew(
            director = "Chad Stahelski",
            cast = listOf("Keanu Reeves", "Michael Nyqvist", "Alfie Allen")
        ),
        "Joker" to MovieCrew(
            director = "Todd Phillips",
            cast = listOf("Joaquin Phoenix", "Robert De Niro", "Zazie Beetz")
        ),
        "Kill Bill" to MovieCrew(
            director = "Quentin Tarantino",
            cast = listOf("Uma Thurman", "David Carradine", "Lucy Liu")
        ),
        "Prestige" to MovieCrew(
            director = "Christopher Nolan",
            cast = listOf("Hugh Jackman", "Christian Bale", "Michael Caine")
        ),
        "Scarface" to MovieCrew(
            director = "Brian De Palma",
            cast = listOf("Al Pacino", "Michelle Pfeiffer", "Steven Bauer")
        ),
        "Star Wars" to MovieCrew(
            director = "George Lucas",
            cast = listOf("Mark Hamill", "Harrison Ford", "Carrie Fisher")
        ),
        "Tenet" to MovieCrew(
            director = "Christopher Nolan",
            cast = listOf("John David Washington", "Robert Pattinson", "Elizabeth Debicki")
        ),
        "The Hateful Eight" to MovieCrew(
            director = "Quentin Tarantino",
            cast = listOf("Samuel L. Jackson", "Kurt Russell", "Jennifer Jason Leigh")
        ),
        "The Pianist" to MovieCrew(
            director = "Roman Polanski",
            cast = listOf("Adrien Brody", "Thomas Kretschmann", "Frank Finlay")
        )
    )
} 
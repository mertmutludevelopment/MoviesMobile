package com.example.moviesmobile.utils

// Utility object for providing movie descriptions
object MovieDescriptions {
    fun getDescription(movieName: String, director: String? = null, year: Int = 0): String {
        return when (movieName) {
            "12 Monkeys" ->
                "A gripping sci-fi mystery where a convict is sent back in time to stop a deadly virus that wiped out humanity. But uncovering the truth in the past proves more challenging than saving the future."

            "Batman" ->
                "In Gotham City, a dark and mysterious vigilante rises to fight crime and confronts the menacing Joker, who threatens to plunge the city into chaos. A classic tale of justice and vengeance."

            "Blade Runner" ->
                "In a dystopian future, a blade runner is tasked with hunting down rogue bioengineered humans known as replicants. As he delves deeper, he begins to question what it truly means to be human."

            "Catsby" ->
                "A tale of ambition, love, and tragedy, where a mysterious millionaire, Jay Gatsby, chases his dream of rekindling a lost romance in the glamorous yet hollow world of 1920s high society."

            "Django" ->
                "Set in the pre-Civil War South, a freed slave teams up with a bounty hunter to rescue his wife from a ruthless plantation owner. A bold and thrilling tale of vengeance and justice."

            "Dune" ->
                "In a distant future, a young nobleman must navigate a treacherous desert planet to protect his family and unlock his destiny. A stunning epic of power, betrayal, and survival."

            "Game" ->
                "A wealthy banker's life takes a dark and unpredictable turn when he participates in a mysterious game. As reality blurs, he must uncover the truth before it's too late. A gripping psychological thriller full of twists and suspense."

            "Harry Potter" ->
                "Follow the journey of a young wizard as he discovers his magical heritage, attends Hogwarts School, and faces the dark wizard Voldemort in an epic battle of good versus evil. A beloved tale of friendship, bravery, and destiny."

            "Hobbit" ->
                "A young hobbit embarks on a perilous journey with a group of dwarves to reclaim a stolen treasure guarded by a fearsome dragon. Filled with adventure, danger, and ancient magic, it's the prelude to the epic \"Lord of the Rings\" saga."

            "Inception" ->
                "A skilled thief who specializes in entering people's dreams is given a chance to have his criminal record erased if he can perform the impossible: planting an idea into someone's mind. A mind-bending thriller that explores the boundaries of reality and the subconscious."

            "Interstellar" ->
                "As Earth faces an impending environmental collapse, a team of astronauts journeys through a wormhole in search of a new habitable planet. A visually stunning and emotionally charged exploration of love, sacrifice, and the survival of humanity across time and space."

            "John Wick" ->
                "A retired hitman seeks vengeance against those who wronged him, unleashing a relentless, action-packed quest for justice. With unmatched combat skills and a fierce resolve, John Wick becomes a legend in the underworld."

            "Joker" ->
                "An exploration of the origins of the iconic villain, Arthur Fleck, a struggling comedian who descends into madness and becomes the notorious Joker. A haunting and thought-provoking tale of societal neglect and the impact of mental illness."

            "Kill Bill" ->
                "A former assassin embarks on a bloody quest for revenge against her former colleagues who betrayed her. With stylish action, intense fight scenes, and a powerful sense of justice, this two-part saga is a tale of vengeance like no other."

            "Prestiege" ->
                "Two magicians in the late 19th century engage in a fierce rivalry to create the ultimate illusion, leading to obsession, betrayal, and tragic consequences. A masterful exploration of ambition and deception."

            "Scarface" ->
                "A Cuban immigrant rises to power in the Miami drug trade, ultimately becoming a ruthless and paranoid criminal mastermind. A raw and intense portrayal of the American Dream turned to corruption."

            "Star Wars" ->
                "A young farm boy embarks on an epic journey to become a Jedi and battle the oppressive Empire. A legendary space opera filled with adventure, friendship, and the fight between good and evil."

            "Tenet" ->
                "A secret agent embarks on a high-stakes mission to prevent World War III, using a mysterious technology that manipulates time. A complex and thrilling story of espionage, time travel, and fate."

            "The Hateful Eight" ->
                "Set in a snowy Wyoming cabin, eight strangers with hidden motives collide as tensions rise and secrets unfold. A tense, violent western thriller directed by Quentin Tarantino."

            "The Pianist" ->
                "The true story of Władysław Szpilman, a Jewish pianist who survives the horrors of the Warsaw Ghetto during World War II. A haunting, emotional tale of resilience and survival."

            else -> "Experience the captivating story of $movieName, a masterfully crafted film directed by $director. This $year release promises to take viewers on an unforgettable journey through its compelling narrative and outstanding performances."
        }
    }
} 
package com.example.movielogger.data

import com.example.movielogger.data.movie.Movie
import com.example.movielogger.data.movie.Person
import java.time.LocalDate

/**
 * TODO: Remove when local storage implemented
 * TEMP UNTIL LOCAL STORAGE IMPLEMENTED
 */
object DataSource {
    fun loadMovies(): List<Movie> {
        return listOf(
            Movie(
                title = "The Lord of the Rings: The Fellowship of the Ring",
                viewedWith = listOf(Person("Caroline Logan")),
                dateViewed = LocalDate.of(2001, 5, 3),
                rating = 10
            ),
            Movie(
                title = "The Lord of the Rings: The Two Towers",
                dateViewed = LocalDate.of(2002, 5, 4),
                rating = 10
            ),
            Movie(
                title = "The Lord of the Rings: The Return of the King"
            ),
            Movie(
                title = "Mission Impossible",
                dateViewed = LocalDate.of(2004, 5, 3),
                rating = 6
            ),
            Movie(
                title = "Zootopia",
                dateViewed = LocalDate.of(2018, 5, 3),
                rating = 8
            ),
        )
    }
}
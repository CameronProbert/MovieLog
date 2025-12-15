package com.example.movielogger.data

import java.time.LocalDate
import java.util.UUID

data class Movie(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val dateAdded: LocalDate? = null,
    val dateViewed: LocalDate? = null,
    val rating: Int? = null,
    val comments: String? = null,
    val viewedWith: List<Person> = emptyList(),
    val suggestedBy: List<Person> = emptyList(),
) {
    val summary: MovieSummary
        get() = MovieSummary(id = id, title = title, dateViewed = dateViewed, rating = rating)
}

data class Person(
    val name: String,
)

data class MovieSummary(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val dateViewed: LocalDate? = null,
    val rating: Int? = null,
)
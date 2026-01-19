package com.example.movielogger.data.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movielogger.data.Dbo
import com.example.movielogger.data.person.Person
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class Movie(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val dateAdded: LocalDate? = null,
    val dateViewed: LocalDate? = null,
    val rating: Int? = null,
    val notes: String? = null,
    val viewedWith: List<Person> = emptyList(),
    val suggestedBy: List<Person> = emptyList(),
) {
    val summary: MovieSummary
        get() = MovieSummary(id = id, title = title, dateViewed = dateViewed, rating = rating)
}

data class MovieSummary(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val dateViewed: LocalDate? = null,
    val rating: Int? = null,
)

@Serializable
@Entity(tableName = "movie")
data class MovieDbo(
    @PrimaryKey val id: UUID,
    val title: String,
    @ColumnInfo(name = "date_added") val dateAdded: String?,
    @ColumnInfo(name = "date_viewed") val dateViewed: String?,
    val rating: Int?,
    val notes: String?,
    @ColumnInfo(name = "viewed_with") val viewedWith: String?,
    @ColumnInfo(name = "suggested_by") val suggestedBy: String?,
): Dbo<Movie> {
    override fun toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            dateAdded = dateAdded.let { LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE) },
            dateViewed = dateViewed.let { LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE) },
            rating = rating,
            notes = notes,
            viewedWith = viewedWith?.split(",")?.map { Person(name = it) } ?: emptyList(),
            suggestedBy = suggestedBy?.split(",")?.map { Person(name = it) } ?: emptyList(),
        )
    }

    companion object {
        fun fromDomain(movie: Movie): MovieDbo {
            return MovieDbo(
                id = movie.id,
                title = movie.title,
                dateAdded = movie.dateAdded?.format(DateTimeFormatter.ISO_LOCAL_DATE),
                dateViewed = movie.dateViewed?.format(DateTimeFormatter.ISO_LOCAL_DATE),
                rating = movie.rating,
                notes = movie.notes,
                viewedWith = movie.viewedWith.joinToString { it.name },
                suggestedBy = movie.suggestedBy.joinToString { it.name },
            )
        }
    }
}


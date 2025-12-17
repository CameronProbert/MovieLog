package com.example.movielogger.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movielogger.data.movie.MovieSummary
import com.example.movielogger.ui.theme.MovieLoggerTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun MovieListItem(
    summary: MovieSummary,
    onSelect: (MovieSummary) -> Unit,
    selectedMovie: Boolean = false
) {

    Box {
        Surface(
            color = if (selectedMovie) MaterialTheme.colorScheme.surfaceBright else MaterialTheme.colorScheme.surface,
            onClick = { onSelect(summary) }
        ) {
            ListItem(
                headlineContent = { Text(text = summary.title) },
                supportingContent = { Text(
                    "Watched: ${summary.dateViewed?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) ?: ""}") },
            )
        }
    }
}

@Preview
@Composable
fun PreviewMovieSummary() {
    MovieLoggerTheme {
        MovieListItem(
            summary = MovieSummary(
                title = "The Lord of the Rings: The Fellowship of the Ring",
                dateViewed = LocalDate.of(2001, 5, 29),
            ),
            onSelect = {},
            selectedMovie = false
        )
    }
}
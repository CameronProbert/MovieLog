package com.example.movielogger.view

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movielogger.data.MovieSummary
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun MovieSummary(summary: MovieSummary) {
    ListItem(
        headlineContent = { Text(text = summary.title) },
        supportingContent = { Text(
            "Watched: ${summary.dateViewed?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) ?: ""}") },
    )
}

@Preview
@Composable
fun previewMovieSummary() {
    MovieSummary(summary = MovieSummary(
        title = "The Lord of the Rings: The Fellowship of the Ring",
        dateViewed = LocalDate.of(2001, 5, 29),
    ))
}
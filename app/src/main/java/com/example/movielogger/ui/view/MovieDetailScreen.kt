package com.example.movielogger.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movielogger.data.movie.Movie
import com.example.movielogger.data.person.Person
import com.example.movielogger.ui.components.DateInputField
import com.example.movielogger.ui.components.StarInput
import com.example.movielogger.ui.theme.MovieLoggerTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(initialMovie: Movie, onSave: (Movie) -> Unit) {
    var title = rememberTextFieldState(initialMovie.title)
    var dateViewed by remember { mutableStateOf(initialMovie.dateViewed) }
    var showDatePicker by remember { mutableStateOf(false) }
    var dateAdded by remember { mutableStateOf(initialMovie.dateAdded ?: LocalDate.now()) }
    var rating by remember { mutableStateOf(initialMovie.rating) }
    var notes = rememberTextFieldState(initialMovie.notes ?: "")

    var submitted by remember { mutableStateOf(false) }

    fun save() {
        submitted = true

        val movie = initialMovie.copy(
            title = title.text.toString(),
            dateViewed = dateViewed,
            rating = rating,
            notes = notes.text.toString(),
            viewedWith = listOf(),
            suggestedBy = listOf(),
            dateAdded = dateAdded,
        )

        onSave(movie)
    }

    Surface {
        Column(modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Title") },
                state = title,
                lineLimits = TextFieldLineLimits.SingleLine,
            )
//            ListItem(
//                modifier = Modifier.fillMaxWidth()
//                    .clickable(onClick = {
//                        showDatePicker = true
//                    }).border(1.dp, Color.DarkGray, MaterialTheme.shapes.extraSmall),
//                overlineContent = { Text(text = "Date Viewed") },
//                headlineContent = {
//                    Row (
//                        modifier = Modifier.padding(top = 4.dp).align(Alignment.Start)
//                    ) {
//                        Icon(
//                            painterResource(R.drawable.event_24px),
//                            contentDescription = "Calendar Icon"
//                        )
//                        Text(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            text = dateViewed?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) ?: "Not seen",
//                        )
//                    }
//                }
//            )
//
//            if (showDatePicker) {
//                DatePickerModal(
//                    initialDate = initialMovie.dateViewed ?: LocalDate.now(),
//                    onDateSelected = {
//                        dateViewed = Instant.ofEpochMilli(it ?: 0).atZone(ZoneId.systemDefault()).toLocalDate()
//                    },
//                    onDismiss = {
//                        showDatePicker = false
//                    }
//                )
//            }
            DateInputField(
                label = "Date Viewed",
                initialDate = dateViewed,
                clearable = true,
            ) { dateViewed = it }

            DateInputField(
                label = "Date Added",
                initialDate = dateAdded,
                clearable = true,
            ) { dateAdded = it }

            Row(verticalAlignment = Alignment.CenterVertically,) {
                Text("Rating: ")
                StarInput(rating) { rating = it }
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Notes") },
                state = notes,
                lineLimits = TextFieldLineLimits.MultiLine(),
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovieLoggerTheme {
        MovieDetailScreen(
            Movie(
                title = "Star Wars Episode IV: A New Hope",
                dateAdded = LocalDate.parse("1990-05-06"),
                dateViewed = LocalDate.parse("1990-05-06"),
                rating = 4,
                notes = "thought it was \npretty good\ntest long\n number of lines",
                viewedWith = listOf(Person(name = "Ben Dark"), Person(name = "Caleb Dark")),
                suggestedBy = listOf()
            ),
            onSave = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    initialDate: LocalDate = LocalDate.now(),
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialDate)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
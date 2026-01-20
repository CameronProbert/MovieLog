package com.example.movielogger.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movielogger.R
import com.example.movielogger.ui.theme.MovieLoggerTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun DateInputField(
    label: String,
    initialDate: LocalDate? = LocalDate.now(),
    clearable: Boolean = false,
    readonly: Boolean = false,
    onDateSelected: (LocalDate) -> Unit
) {
    var selectedDate by remember { mutableStateOf(initialDate) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
            ?.atStartOfDay(ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli()
    )

    var showDialog by remember { mutableStateOf(false) }

    fun openDialog() {
        if (!readonly) {
            showDialog = true
        }
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        val newDate = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        selectedDate = newDate
                        onDateSelected(newDate)
                    }
                    showDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Box {
        OutlinedTextField(
            value = selectedDate?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) ?: "",
            onValueChange = {},
            label = { Text(label) },
            readOnly = false,
            enabled = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = { openDialog() }) {
                    Icon(painterResource(R.drawable.event_24px), contentDescription = null)
                }
            },
            trailingIcon = {
                if (clearable && selectedDate != null) {
                    IconButton(onClick = { selectedDate = null }) {
                        Icon(
                            painterResource(R.drawable.close_24px),
                            contentDescription = "Clear date"
                        )
                    }
                }
            },
        )

        if (selectedDate == null) {
            Box(modifier = Modifier.matchParentSize().clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = true
            ) {
                openDialog()
            }) {}
        }
    }



}

@Preview(showBackground = true)
@Composable
fun DateInputFieldPreviewNoDate() {
    MovieLoggerTheme {
        DateInputField(
            label = "Date Viewed",
            initialDate = null,
            clearable = true,
            readonly = false
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun DateInputFieldPreview2() {
    MovieLoggerTheme {
        DateInputField(
            label = "Date",
            initialDate = LocalDate.of(2021, 1, 1),
            clearable = true,
            readonly = false
        ) { }
    }
}
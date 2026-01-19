package com.example.movielogger.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.movielogger.R
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

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

    OutlinedTextField(
        value = selectedDate.toString(),
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (!readonly) {
                    showDialog = true
                }
            },
        leadingIcon = {
            Icon(painterResource(R.drawable.event_24px), contentDescription = null)
        },
        trailingIcon = {
            if (clearable && selectedDate != null) {
                IconButton(onClick = { selectedDate = null }) { }
                Icon(
                    painterResource(R.drawable.close_24px),
                    contentDescription = "Clear date")
            }
        }
    )
}
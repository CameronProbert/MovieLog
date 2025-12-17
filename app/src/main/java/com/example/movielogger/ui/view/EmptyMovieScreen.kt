package com.example.movielogger.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmptyMovieScreen() {
    Surface(modifier = Modifier.padding(all = 24.dp)) {
        Column {
            Text(
                textAlign = TextAlign.Center,
                text = "Select the \"New Movie\" button to get started!",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
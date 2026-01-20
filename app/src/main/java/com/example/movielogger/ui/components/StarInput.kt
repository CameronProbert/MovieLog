package com.example.movielogger.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movielogger.R
import com.example.movielogger.ui.theme.MovieLoggerTheme

@Composable
fun StarInput(
    initialRating: Int?,
    readonly: Boolean = false,
    onChange: (Int) -> Unit = {},
) {
    var selectedRating by remember { mutableStateOf(initialRating) }

    fun onRatingChange(starNum: Int) {
        if (!readonly) {
            selectedRating = starNum
            onChange(starNum)
        }
    }

    Row (horizontalArrangement = Arrangement.spacedBy((-8).dp)) {
        (1..5).forEach { starNum ->
            IconButton(onClick = {
                onRatingChange(starNum)
            },
                enabled = !readonly) {
                val icon = when {
                    selectedRating == null -> R.drawable.star_24px
                    starNum <= selectedRating!! -> R.drawable.star_filled_24px
                    else -> R.drawable.star_24px
                }

                Icon(painterResource(id = icon), contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarInputPreviewEnabled() {
    MovieLoggerTheme {
        StarInput(
            initialRating = 3,
            readonly = false
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun StarInputPreviewDisabled() {
    MovieLoggerTheme {
        StarInput(
            initialRating = 3,
            readonly = true
        ) {}
    }
}
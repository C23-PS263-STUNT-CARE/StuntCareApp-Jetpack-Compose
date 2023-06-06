package com.febiarifin.stuntcare.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ShowSnackBar(
    message: String,
) {
    Snackbar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.Black,
        contentColor = Color.White,
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center
        )
    }
}
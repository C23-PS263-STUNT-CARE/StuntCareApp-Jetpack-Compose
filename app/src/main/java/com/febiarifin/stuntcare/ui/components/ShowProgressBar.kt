package com.febiarifin.stuntcare.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun ShowProgressBar(
    state: Boolean,
    isFillMaxSize: Boolean = false
) {

    if (isFillMaxSize) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (state) {
                CircularProgressIndicator(
                    color = Color(0xFF3984E9),
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }else{
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
        ) {
            if (state) {
                CircularProgressIndicator(
                    color = Color(0xFF3984E9),
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    StuntCareTheme {
        ShowProgressBar(state = true)
    }
}
package com.febiarifin.stuntcare.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.febiarifin.stuntcare.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadGlideImage(
    path: String,
    height: Dp,
) {
    GlideImage(
        model = path,
        contentDescription = "Image",
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
    ){
        it
            .error(R.drawable.lazy_load)
            .placeholder(R.drawable.lazy_load)
            .load(path)
    }
}
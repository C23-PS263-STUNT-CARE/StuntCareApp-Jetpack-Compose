package com.febiarifin.stuntcare.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.febiarifin.stuntcare.R

data class Banner(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int,
)

val dummyBanner = listOf(
    R.drawable.banner_image_1 to R.string.banner_1,
    R.drawable.banner_image_2 to R.string.banner_2,
    R.drawable.banner_image_3 to R.string.banner_3,
).map { Banner(it.first, it.second) }

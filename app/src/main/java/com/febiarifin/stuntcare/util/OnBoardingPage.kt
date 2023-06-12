package com.febiarifin.stuntcare.util

import androidx.annotation.DrawableRes
import com.febiarifin.stuntcare.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.banner_header,
        title = "Stuntcare",
        description = "With StuntCare, you can help your baby to live a healthier life"
    )

    object Second : OnBoardingPage(
        image = R.drawable.splash_image_1,
        title = "Take care",
        description = "By taking care of your baby is a form of extraordinary love from you"
    )

    object Third : OnBoardingPage(
        image = R.drawable.splash_image_2,
        title = "Healthy",
        description = "Keeping the baby healthy is one of the ways parents get happiness"
    )
}

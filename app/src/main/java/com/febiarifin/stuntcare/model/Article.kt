package com.febiarifin.stuntcare.model

import com.febiarifin.stuntcare.R

data class Article(
    val image: Int,
    val title: String,
    val label: String,
    val date: String,
)

val dummyArticle = listOf(
    Article(R.drawable.article_image_1, "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023"),
    Article(R.drawable.article_image_2, "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023"),
    Article(R.drawable.article_image_2, "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023")
)
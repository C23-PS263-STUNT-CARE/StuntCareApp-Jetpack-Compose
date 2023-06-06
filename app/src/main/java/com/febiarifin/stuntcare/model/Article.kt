package com.febiarifin.stuntcare.model

import com.febiarifin.stuntcare.R

data class Article(
    val id: Long,
    val image: Int,
    val title: String,
    val label: String,
    val date: String,
    val content: String,
    val author: String,
)

val dummyArticle = listOf(
    Article(1,R.drawable.article_image_1, "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit","Admin"),
    Article(2,R.drawable.article_image_2, "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit","Admin"),
    Article(3,R.drawable.article_image_3, "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit","Admin"),
)
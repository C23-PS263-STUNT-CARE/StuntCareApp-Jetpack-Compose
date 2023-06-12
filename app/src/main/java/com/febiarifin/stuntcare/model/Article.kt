package com.febiarifin.stuntcare.model

data class Article(
    val id: Long,
    val image_url: String,
    val title: String,
    val label: String,
    val published_at: String,
    val content: String,
    val author: String,
)

val dummyArticle = listOf(
    Article(1,"", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit","Admin"),
    Article(2,"", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit","Admin"),
    Article(3,"", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit", "Lorem", "29 Mei 2023", "Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit Lorem ipsum dolor sit amet conse ctetur adipiscing elit","Admin"),
)
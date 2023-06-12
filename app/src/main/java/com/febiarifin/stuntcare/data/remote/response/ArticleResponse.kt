package com.febiarifin.stuntcare.data.remote.response

import com.febiarifin.stuntcare.model.Article

data class ArticleResponse(
    val error: Boolean,
    val message: String,
    val data: List<Article>,
)

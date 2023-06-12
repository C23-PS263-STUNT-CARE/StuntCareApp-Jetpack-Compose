package com.febiarifin.stuntcare.data.remote.response

import com.febiarifin.stuntcare.model.Article

data class ArticleByIdResponse(
    val error: Boolean,
    val message: String,
    val data: Article,
)

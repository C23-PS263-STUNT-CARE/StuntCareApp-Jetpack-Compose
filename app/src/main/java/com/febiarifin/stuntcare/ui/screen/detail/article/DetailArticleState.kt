package com.febiarifin.stuntcare.ui.screen.detail.article

import com.febiarifin.stuntcare.data.remote.response.ArticleByIdResponse
import com.febiarifin.stuntcare.model.Article

data class DetailArticleState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val data: ArticleByIdResponse? = null,
)

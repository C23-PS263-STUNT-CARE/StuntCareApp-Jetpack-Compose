package com.febiarifin.stuntcare.ui.screen.home

import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.Info

data class ArticleState(
    val loading: Boolean? = false,
    val errorMessage: String? = null,
    val data: List<Article>? = null,
)

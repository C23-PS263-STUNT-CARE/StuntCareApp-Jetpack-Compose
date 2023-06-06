package com.febiarifin.stuntcare.data

import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.dummyArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ArticleRepository {
    private val listArticle = mutableListOf<Article>()

    init {
        if (listArticle.isEmpty()) {
            dummyArticle.forEach { acticle ->
                listArticle.add(acticle)
            }
        }
    }

    fun getAllArticle(): Flow<List<Article>> {
        return flowOf(listArticle)
    }

    fun getArticleById(articleId: Long): Article {
        return listArticle.first{
            it.id == articleId
        }
    }

    companion object{
        @Volatile
        private var instance: ArticleRepository ?= null

        fun getInstance(): ArticleRepository =
            instance ?: synchronized(this){
                ArticleRepository().apply {
                    instance = this
                }
            }
    }
}
package com.febiarifin.stuntcare.data.repository

import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.model.dummyArticle
import com.febiarifin.stuntcare.model.dummyCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val listCheck = mutableListOf<Check>()
    private val listArticle = mutableListOf<Article>()
    private val message: String = "Login Berhasil"

    init {
        if (listCheck.isEmpty()) {
            dummyCheck.forEach { check ->
                listCheck.add(check)
            }
        }
    }

    fun getAllCheck(): Flow<List<Check>> {
        return flowOf(listCheck)
    }

    fun getCheckById(checkId: Long): Check {
        return listCheck.first{
            it.id == checkId
        }
    }

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
        private var instance: Repository?= null

        fun getInstance(): Repository =
            instance ?: synchronized(this){
                Repository().apply {
                    instance = this
                }
            }
    }
}
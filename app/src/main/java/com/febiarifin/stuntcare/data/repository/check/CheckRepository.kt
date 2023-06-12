package com.febiarifin.stuntcare.data.repository.check

import com.febiarifin.stuntcare.data.remote.response.ArticleByIdResponse
import com.febiarifin.stuntcare.data.remote.response.CheckResponse
import com.febiarifin.stuntcare.data.remote.response.DeleteResponse
import com.febiarifin.stuntcare.data.remote.response.InfoResponse
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.model.Info
import com.febiarifin.stuntcare.util.Result
import kotlinx.coroutines.flow.Flow

interface CheckRepository {

    fun getAllCheckHistory(token: String, userId: String): Flow<Result<List<Check>>>

    fun getStuntingById(token: String, userId: String, checkId: Int): Flow<Result<CheckResponse>>

    fun checkStunting(
        token: String,
        userId: String,
        name: String,
        sex: String,
        age: Int,
        birthWeight: Double,
        birthLength: Double,
        bodyWeight: Double,
        bodyLength: Double,
        asiEksklusif: String
    ): Flow<Result<CheckResponse>>

    fun updateStunting(
        token: String,
        userId: String,
        checkId: Int,
        name: String,
        sex: String,
        age: Int,
        birthWeight: Double,
        birthLength: Double,
        bodyWeight: Double,
        bodyLength: Double,
        asiEksklusif: String
    ): Flow<Result<CheckResponse>>

    fun deleteStuntingById(token: String, userId: String, checkId: Int): Flow<Result<DeleteResponse>>

    fun getAllInfo(token: String): Flow<Result<List<Info>>>

    fun getAllArticle(token: String): Flow<Result<List<Article>>>

    fun getAllArticleLatest(token: String): Flow<Result<List<Article>>>

    fun getArticleById(token: String, articleId: Int): Flow<Result<ArticleByIdResponse>>

}
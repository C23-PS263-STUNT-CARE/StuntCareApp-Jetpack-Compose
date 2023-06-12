package com.febiarifin.stuntcare.data.remote.retrofit

import com.febiarifin.stuntcare.data.remote.response.ArticleByIdResponse
import com.febiarifin.stuntcare.data.remote.response.ArticleResponse
import com.febiarifin.stuntcare.data.remote.response.CheckHistoryResponse
import com.febiarifin.stuntcare.data.remote.response.CheckResponse
import com.febiarifin.stuntcare.data.remote.response.DeleteResponse
import com.febiarifin.stuntcare.data.remote.response.InfoResponse
import com.febiarifin.stuntcare.data.remote.response.LoginResponse
import com.febiarifin.stuntcare.data.remote.response.RegisterResponse
import com.febiarifin.stuntcare.util.Constants
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST(Constants.LOGIN_URL)
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST(Constants.REGISTER_URL)
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confPassword") confPassword: String,
    ): RegisterResponse

    @GET(Constants.CHECK_HISTORY)
    suspend fun getAllCheckHistory(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
    ): CheckHistoryResponse

    @GET(Constants.CHECK_GET_UPDATE_DELETE)
    suspend fun getStuntingById(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("checkId") checkId: Int,
    ): CheckResponse

    @DELETE(Constants.CHECK_GET_UPDATE_DELETE)
    suspend fun deleteStuntingById(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("checkId") checkId: Int,
    ): DeleteResponse

    @FormUrlEncoded
    @POST(Constants.CHECK_POST)
    suspend fun checkStunting(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Field("name") name: String,
        @Field("sex") sex: String,
        @Field("age") age: Int,
        @Field("birth_weight") birthWeight: Double,
        @Field("birth_length") birthLength: Double,
        @Field("body_weight") bodyWeight: Double,
        @Field("body_length") bodyLength: Double,
        @Field("asi_eksklusif") asiEksklusif: String,
    ): CheckResponse
    @FormUrlEncoded
    @PUT(Constants.CHECK_GET_UPDATE_DELETE)
    suspend fun updateStunting(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("checkId") checkId: Int,
        @Field("name") name: String,
        @Field("sex") sex: String,
        @Field("age") age: Int,
        @Field("birth_weight") birthWeight: Double,
        @Field("birth_length") birthLength: Double,
        @Field("body_weight") bodyWeight: Double,
        @Field("body_length") bodyLength: Double,
        @Field("asi_eksklusif") asiEksklusif: String,
    ): CheckResponse

    @GET(Constants.GET_INFO)
    suspend fun getAllInfo(
        @Header("Authorization") token: String,
    ): InfoResponse

    @GET(Constants.GET_ALL_ARTICLE_LATEST)
    suspend fun getAllArticleLatest(
        @Header("Authorization") token: String,
    ): ArticleResponse

    @GET(Constants.GET_ALL_ARTICLE)
    suspend fun getAllArticle(
        @Header("Authorization") token: String,
    ): ArticleResponse

    @GET(Constants.GET_ARTICLE_BY_ID)
    suspend fun getAllArticleById(
        @Header("Authorization") token: String,
        @Path("articleId") articleId: Int,
    ): ArticleByIdResponse
}
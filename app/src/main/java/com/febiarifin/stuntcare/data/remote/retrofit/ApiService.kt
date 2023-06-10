package com.febiarifin.stuntcare.data.remote.retrofit

import com.febiarifin.stuntcare.data.remote.response.CheckHistoryResponse
import com.febiarifin.stuntcare.data.remote.response.LoginResponse
import com.febiarifin.stuntcare.data.remote.response.RegisterResponse
import com.febiarifin.stuntcare.util.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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


    @FormUrlEncoded
    @GET(Constants.CHECK_HISTORY)
    suspend fun getAllCheckHistory(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
    ): CheckHistoryResponse
}
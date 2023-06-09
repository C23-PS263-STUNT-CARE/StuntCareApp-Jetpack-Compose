package com.febiarifin.stuntcare.data.remote.retrofit

import com.febiarifin.stuntcare.data.remote.request.LoginRequest
import com.febiarifin.stuntcare.data.remote.request.RegisterRequest
import com.febiarifin.stuntcare.data.remote.response.LoginResponse
import com.febiarifin.stuntcare.data.remote.response.RegisterResponse
import com.febiarifin.stuntcare.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.LOGIN_URL)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST(Constants.REGISTER_URL)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @FormUrlEncoded
    @POST(Constants.LOGIN_URL)
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

}
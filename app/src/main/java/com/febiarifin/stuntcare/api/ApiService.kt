package com.febiarifin.stuntcare.api

import com.febiarifin.stuntcare.model.request.LoginRequest
import com.febiarifin.stuntcare.model.response.LoginResponse
import com.febiarifin.stuntcare.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.LOGIN_URL)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

}
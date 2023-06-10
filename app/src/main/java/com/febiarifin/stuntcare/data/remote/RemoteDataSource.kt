package com.febiarifin.stuntcare.data.remote

import com.febiarifin.stuntcare.data.remote.response.LoginResponse
import com.febiarifin.stuntcare.data.remote.response.RegisterResponse
import com.febiarifin.stuntcare.data.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun loginUser(email:String, password: String): LoginResponse{
        return apiService.loginUser(email, password)
    }

    suspend fun registerUser(name: String, email:String, password: String, confPassword: String): RegisterResponse{
        return apiService.registerUser(name, email, password, confPassword)
    }

}
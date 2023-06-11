package com.febiarifin.stuntcare.data.remote

import com.febiarifin.stuntcare.data.remote.response.CheckHistoryResponse
import com.febiarifin.stuntcare.data.remote.response.CheckResponse
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

    suspend fun getAllCheckHistory(token: String, userId: String): CheckHistoryResponse{
        return apiService.getAllCheckHistory(token, userId)
    }

    suspend fun getStuntingById(token: String, userId: String, checkId: Int): CheckResponse{
        return apiService.getStuntingById(token, userId, checkId)
    }

    suspend fun checkStunting(token: String,userId: String, name: String, sex: String, age: Int, birthWeight: Double, birthLength: Double, bodyWeight: Double, bodyLength: Double, asiEksklusif: String): CheckResponse{
        return apiService.checkStunting(token,userId, name, sex, age, birthWeight, birthLength, bodyWeight, bodyLength, asiEksklusif)
    }

}
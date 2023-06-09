package com.febiarifin.stuntcare.data.repository.auth

import com.febiarifin.stuntcare.data.remote.response.LoginResponse
import com.febiarifin.stuntcare.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun loginUser(email: String, password: String): Flow<Result<LoginResponse>>

}
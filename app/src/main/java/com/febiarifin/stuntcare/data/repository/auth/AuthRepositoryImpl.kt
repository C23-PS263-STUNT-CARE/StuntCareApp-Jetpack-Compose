package com.febiarifin.stuntcare.data.repository.auth

import com.febiarifin.stuntcare.data.remote.RemoteDataSource
import com.febiarifin.stuntcare.data.remote.response.LoginResponse
import com.febiarifin.stuntcare.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): AuthRepository {

    override fun loginUser(email: String, password: String): Flow<Result<LoginResponse>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = remoteDataSource.loginUser(email, password)
                emit(Result.Success(response))
            }catch (e: Exception){
                emit(Result.Error("Terjadi Kesalahan"))
            }
        }
    }
}
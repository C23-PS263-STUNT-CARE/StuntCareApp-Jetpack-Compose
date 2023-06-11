package com.febiarifin.stuntcare.data.repository.check

import com.febiarifin.stuntcare.data.remote.RemoteDataSource
import com.febiarifin.stuntcare.data.remote.response.CheckResponse
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : CheckRepository {

    override fun getAllCheckHistory(token: String, userId: String): Flow<Result<List<Check>>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = remoteDataSource.getAllCheckHistory(token, userId)
                emit(Result.Success(response.data))
            } catch (e: Exception) {
                emit(Result.Error("Terjadi Kesalahan"))
            }
        }
    }

    override fun getStuntingById(
        token: String,
        userId: String,
        checkId: Int
    ): Flow<Result<CheckResponse>> {
        return flow {
            emit(Result.Loading())
            try{
                val response = remoteDataSource.getStuntingById(token, userId, checkId)
                emit(Result.Success(response))
            }catch (e: Exception){
                emit(Result.Error("Terjadi Kesalahan"))
            }
        }
    }

    override fun checkStunting(
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
    ): Flow<Result<CheckResponse>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = remoteDataSource.checkStunting(
                    token,
                    userId,
                    name,
                    sex,
                    age,
                    birthWeight,
                    birthLength,
                    bodyWeight,
                    bodyLength,
                    asiEksklusif
                )
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error("Terjadi Kesalahan"))
            }
        }
    }

}
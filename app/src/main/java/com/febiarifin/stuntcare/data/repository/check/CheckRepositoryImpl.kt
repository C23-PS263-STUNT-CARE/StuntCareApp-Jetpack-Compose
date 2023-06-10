package com.febiarifin.stuntcare.data.repository.check

import com.febiarifin.stuntcare.data.remote.RemoteDataSource
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
            }catch (e: Exception){
                emit(Result.Error("Terjadi Kesalahan"))
            }
        }
    }

}
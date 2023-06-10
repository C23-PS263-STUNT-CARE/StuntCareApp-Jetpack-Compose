package com.febiarifin.stuntcare.data.repository.check

import com.febiarifin.stuntcare.data.remote.response.CheckResponse
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.util.Result
import kotlinx.coroutines.flow.Flow

interface CheckRepository {

    fun getAllCheckHistory(token: String, userId: String): Flow<Result<List<Check>>>

    fun checkStunting(
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
    ): Flow<Result<CheckResponse>>

}
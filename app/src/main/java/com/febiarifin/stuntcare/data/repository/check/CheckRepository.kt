package com.febiarifin.stuntcare.data.repository.check

import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.util.Result
import kotlinx.coroutines.flow.Flow

interface CheckRepository {

    fun getAllCheckHistory(token: String, userId: String): Flow<Result<List<Check>>>

}
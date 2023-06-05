package com.febiarifin.stuntcare.data

import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.model.dummyCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CheckRepository {
    private val listCheck = mutableListOf<Check>()

    init {
        if (listCheck.isEmpty()) {
            dummyCheck.forEach { check ->
                listCheck.add(check)
            }
        }
    }

    fun getAllCheck(): Flow<List<Check>>{
        return flowOf(listCheck)
    }

    fun getCheckById(checkId: Long): Check{
        return listCheck.first{
            it.id == checkId
        }
    }

    companion object{
        @Volatile
        private var instance: CheckRepository ?= null

        fun getInstance(): CheckRepository =
            instance ?: synchronized(this){
                CheckRepository().apply {
                    instance = this
                }
            }
    }
}
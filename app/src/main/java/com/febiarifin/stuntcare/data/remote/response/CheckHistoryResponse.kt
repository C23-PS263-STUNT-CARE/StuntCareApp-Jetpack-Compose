package com.febiarifin.stuntcare.data.remote.response

import com.febiarifin.stuntcare.model.Check

data class CheckHistoryResponse(
    val data: List<Check>,
    val error: Boolean,
    val message: String
)
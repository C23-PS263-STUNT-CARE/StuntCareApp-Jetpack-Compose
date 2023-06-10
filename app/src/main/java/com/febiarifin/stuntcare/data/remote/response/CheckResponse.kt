package com.febiarifin.stuntcare.data.remote.response

import com.febiarifin.stuntcare.model.Check

data class CheckResponse(
    val error: Boolean,
    val message: String,
    val data: Check,
)

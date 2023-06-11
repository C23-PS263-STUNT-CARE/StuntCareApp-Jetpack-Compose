package com.febiarifin.stuntcare.data.remote.response

data class DeleteResponse(
    val error: Boolean,
    val message: String,
    val data: String? = null,
)

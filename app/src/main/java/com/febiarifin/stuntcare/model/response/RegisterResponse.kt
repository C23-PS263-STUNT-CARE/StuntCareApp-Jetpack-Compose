package com.febiarifin.stuntcare.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val error: Boolean,
    val message: String,
)

package com.febiarifin.stuntcare.data.remote.response

import com.febiarifin.stuntcare.model.User

data class LoginResponse(
    val `data`: User,
    val error: Boolean,
    val message: String
)

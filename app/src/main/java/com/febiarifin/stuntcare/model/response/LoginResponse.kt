package com.febiarifin.stuntcare.model.response

import com.febiarifin.stuntcare.model.User

data class LoginResponse(
    val results: Results
)

data class Results(
    val `data`: User,
    val error: Boolean,
    val message: String
)

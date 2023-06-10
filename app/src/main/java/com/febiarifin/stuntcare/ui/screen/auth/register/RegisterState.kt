package com.febiarifin.stuntcare.ui.screen.auth.register

import com.febiarifin.stuntcare.data.remote.response.RegisterResponse

data class RegisterState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val data: RegisterResponse? = null,
)

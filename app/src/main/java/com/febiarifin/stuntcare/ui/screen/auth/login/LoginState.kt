package com.febiarifin.stuntcare.ui.screen.auth.login

import com.febiarifin.stuntcare.data.remote.response.LoginResponse

data class LoginState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val data: LoginResponse? = null,
)

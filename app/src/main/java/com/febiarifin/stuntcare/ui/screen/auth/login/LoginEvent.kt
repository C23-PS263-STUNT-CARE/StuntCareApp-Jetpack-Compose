package com.febiarifin.stuntcare.ui.screen.auth.login

sealed class LoginEvent{

    data class OnSignIn(
        val email: String,
        val password: String,
    ): LoginEvent()

}

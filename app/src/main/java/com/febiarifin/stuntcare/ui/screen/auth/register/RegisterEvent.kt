package com.febiarifin.stuntcare.ui.screen.auth.register

sealed class RegisterEvent{

    data class OnSignUp(
        val name: String,
        val email: String,
        val password: String,
        val confPassword: String,
    ): RegisterEvent()

}
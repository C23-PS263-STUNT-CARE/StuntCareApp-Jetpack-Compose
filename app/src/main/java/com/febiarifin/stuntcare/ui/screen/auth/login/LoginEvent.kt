package com.febiarifin.stuntcare.ui.screen.auth.login

import com.febiarifin.stuntcare.model.auth.SignInResult

sealed class LoginEvent{

    data class OnSignIn(
        val email: String,
        val password: String,
    ): LoginEvent()

//    data class OnSignInResult(
//        val signInResult: SignInResult,
//    ): LoginEvent()

}

package com.febiarifin.stuntcare.model.request

data class RegisterRequest(
    var name: String,
    var email: String,
    var password: String,
    var confPassword: String,
)

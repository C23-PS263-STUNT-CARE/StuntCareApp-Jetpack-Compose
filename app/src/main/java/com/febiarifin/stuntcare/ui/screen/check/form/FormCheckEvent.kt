package com.febiarifin.stuntcare.ui.screen.check.form

sealed class FormCheckEvent{

    data class OnCheckStunting(
        val token: String,
        val userId: String,
        val name: String,
        val sex: String,
        val age: Int,
        val birth_weight: Double,
        val birth_length: Double,
        val body_weight: Double,
        val body_length: Double,
        val asi_ekslusif: String,
    ): FormCheckEvent()

}

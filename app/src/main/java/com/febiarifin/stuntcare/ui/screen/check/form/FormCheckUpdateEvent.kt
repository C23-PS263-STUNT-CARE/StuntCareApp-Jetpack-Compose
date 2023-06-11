package com.febiarifin.stuntcare.ui.screen.check.form

sealed class FormCheckUpdateEvent{

    data class OnUpdateStunting(
        val token: String,
        val userId: String,
        val checkId: Int,
        val name: String,
        val sex: String,
        val age: Int,
        val birth_weight: Double,
        val birth_length: Double,
        val body_weight: Double,
        val body_length: Double,
        val asi_ekslusif: String,
    ): FormCheckUpdateEvent()

}

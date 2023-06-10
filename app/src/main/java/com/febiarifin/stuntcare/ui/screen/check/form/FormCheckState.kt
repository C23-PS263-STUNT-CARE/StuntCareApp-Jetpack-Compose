package com.febiarifin.stuntcare.ui.screen.check.form

import com.febiarifin.stuntcare.data.remote.response.CheckResponse

data class FormCheckState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val data: CheckResponse? = null,
)

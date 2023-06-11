package com.febiarifin.stuntcare.ui.screen.detail.check

import com.febiarifin.stuntcare.data.remote.response.CheckResponse

data class DetailCheckState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val data: CheckResponse? = null,
)

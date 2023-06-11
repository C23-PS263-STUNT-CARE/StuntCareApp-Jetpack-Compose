package com.febiarifin.stuntcare.ui.screen.home

import com.febiarifin.stuntcare.model.Info

data class InfoState(
    val loading: Boolean? = false,
    val errorMessage: String? = null,
    val data: List<Info>? = null,
)

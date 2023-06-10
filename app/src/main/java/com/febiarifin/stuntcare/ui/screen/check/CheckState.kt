package com.febiarifin.stuntcare.ui.screen.check

import com.febiarifin.stuntcare.model.Check

data class CheckState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val data: List<Check>? = null,
)

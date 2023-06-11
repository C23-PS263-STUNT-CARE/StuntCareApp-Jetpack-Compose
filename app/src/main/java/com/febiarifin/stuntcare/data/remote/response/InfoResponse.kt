package com.febiarifin.stuntcare.data.remote.response

import com.febiarifin.stuntcare.model.Info

data class InfoResponse(
    val error: Boolean,
    val message: String,
    val data : List<Info>,
)

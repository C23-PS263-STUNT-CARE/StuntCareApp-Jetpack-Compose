package com.febiarifin.stuntcare.ui.common

sealed class BackPress{
    object Idle : BackPress()
    object InitialTouch : BackPress()
}

package com.febiarifin.stuntcare.di

import com.febiarifin.stuntcare.data.CheckRepository

object Injection {
    fun provideCheckRepository(): CheckRepository{
        return CheckRepository.getInstance()
    }
}
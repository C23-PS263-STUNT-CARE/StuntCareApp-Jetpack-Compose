package com.febiarifin.stuntcare.di

import com.febiarifin.stuntcare.data.Repository

object Injection {
    fun provideRepository(): Repository{
        return Repository.getInstance()
    }
}
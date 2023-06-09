package com.febiarifin.stuntcare.di

import com.febiarifin.stuntcare.data.repository.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}
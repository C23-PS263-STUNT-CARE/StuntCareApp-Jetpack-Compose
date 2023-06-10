package com.febiarifin.stuntcare.di

import com.febiarifin.stuntcare.data.repository.auth.AuthRepository
import com.febiarifin.stuntcare.data.repository.auth.AuthRepositoryImpl
import com.febiarifin.stuntcare.data.repository.check.CheckRepository
import com.febiarifin.stuntcare.data.repository.check.CheckRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun provideCheckRepository(checkRepositoryImpl: CheckRepositoryImpl): CheckRepository
}
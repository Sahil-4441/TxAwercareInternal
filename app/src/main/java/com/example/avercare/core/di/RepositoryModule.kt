package com.example.avercare.core.di

import com.example.avercare.data.repository.AuthRepositoryImpl
import com.example.avercare.data.repository.ExampleRepositoryImpl
import com.example.avercare.domain.repository.AuthRepository
import com.example.avercare.domain.repository.ExampleRepository
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
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindExampleRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository
}

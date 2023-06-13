package com.rg.capstone.di

import com.google.gson.GsonBuilder
import com.rg.capstone.network.ApiService
import com.rg.capstone.network.model.UserDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideUserMapper(): UserDtoMapper {
        return UserDtoMapper()
    }

    @Provides
    @Singleton //scope
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://34.128.80.107/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }
}
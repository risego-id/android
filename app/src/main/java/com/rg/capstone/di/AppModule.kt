package com.rg.capstone.di

import android.app.Application
import com.rg.capstone.data.remote.RGApi
import com.rg.capstone.data.repository.RGRepositoryImpl
import com.rg.capstone.domain.repository.RGRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton //scope
    fun provideApi(): RGApi {
        return Retrofit.Builder()
            .baseUrl("https://test.com")
            .build()
            .create(RGApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: RGApi, application: Application): RGRepository {
        return RGRepositoryImpl(api, application)
    }
}
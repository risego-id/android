package com.rg.capstone.di

import android.app.Application
import android.content.Context
import com.rg.capstone.RiseGoApplication
import com.rg.capstone.network.ApiService
import com.rg.capstone.repository.RGRepository
import com.rg.capstone.repository.RGRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context): RiseGoApplication {
        return app as RiseGoApplication
    }

//    fun provideRepository(api: ApiService, application: Application): RGRepository {
//        return RGRepositoryImpl(api, application)
//    }
}
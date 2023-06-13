package com.rg.capstone.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.rg.capstone.domain.UserPreference
import com.rg.capstone.network.ApiService
import com.rg.capstone.network.model.UserDtoMapper
import com.rg.capstone.repository.RGRepository
import com.rg.capstone.repository.RGRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.datastore.preferences.preferencesDataStore


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

//    @Provides
//    @Singleton
//    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
//        PreferenceDataStoreFactory.create(
//            produceFile = {
//                appContext.preferencesDataStoreFile("token")
//            }
//        )

//    @Singleton
//    @get:Provides
//    val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "settings")

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> = appContext.dataStore


    @Provides
    @Singleton
    fun provideUserPreference(dataStore: DataStore<Preferences>): UserPreference {
        return UserPreference(dataStore)
    }

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService,
        userDtoMapper: UserDtoMapper,
        pref: UserPreference
    ): RGRepository {
        return RGRepositoryImpl(apiService, userDtoMapper, pref)
    }
}
package com.rg.capstone.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
import javax.inject.Singleton

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")
val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "settings")
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DataStoreModule {
//    @Provides
//    @Singleton
//    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
//        return context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")
//}
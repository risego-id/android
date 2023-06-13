package com.rg.capstone.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import androidx.datastore.preferences.core.intPreferencesKey

class UserPreference @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    ){
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USERID_KEY = intPreferencesKey("userid")
    }

    fun getUserToken(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    fun getUserId(): Flow<Int?> = dataStore.data.map {
        it[USERID_KEY]
    }

    suspend fun saveUser(token: String, id: Int) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USERID_KEY] = id
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

//    companion object {
//        private const val PREF_NAME = "user_pref"
//        private const val KEY_USER_TOKEN = "user_token"
//    }
//
//    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//
//    fun saveUser(token: String) {
//        sharedPreferences.edit {
//            putString(KEY_USER_TOKEN, token)
//        }
//    }
//
//    fun getUser(): Flow<String?> {
//        return sharedPreferences.getString(KEY_USER_TOKEN, null)?.let { token ->
//            flowOf(token)
//        } ?: flowOf(null)
//    }

//    companion object {
//        @Volatile
//        private var INSTANCE: UserPreference? = null
//
//        private val TOKEN_KEY = stringPreferencesKey("token")
//        private val USERID_KEY = intPreferencesKey("userid")
//
//        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
//            return INSTANCE ?: synchronized(this) {
//                val instance = UserPreference(dataStore)
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}
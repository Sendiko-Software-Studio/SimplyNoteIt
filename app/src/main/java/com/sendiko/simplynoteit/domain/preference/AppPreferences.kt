package com.sendiko.simplynoteit.domain.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val nameKey = stringPreferencesKey("username")
    private val tokenKey = stringPreferencesKey("auth_token")
    private val userIdKey = stringPreferencesKey("id")

    fun getId(): Flow<String> {
        return dataStore.data.map {
            it[userIdKey]?:""
        }
    }

    suspend fun setId(id: String) {
        dataStore.edit {
            it[userIdKey] = id
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map {
            it[nameKey]?:""
        }
    }

    suspend fun setName(name: String) {
        dataStore.edit {
            it[nameKey] = name
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[tokenKey]?:""
        }
    }

    suspend fun setToken(token: String){
        dataStore.edit {
            it[tokenKey] = token
        }
    }
}
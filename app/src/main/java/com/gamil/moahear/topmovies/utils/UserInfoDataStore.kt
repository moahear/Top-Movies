package com.gamil.moahear.topmovies.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserInfoDataStore @Inject constructor(@ApplicationContext val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_INFO_DATA_STORE)
        val userToken = stringPreferencesKey(Constants.USER_TOKEN)
    }

    suspend fun saveUserInfo(token: String) {
        context.dataStore.edit {
            it[userToken] = token
        }
    }

    fun getUserInfo(): Flow<Preferences> {
        return context.dataStore.data
    }

    fun getUserToken(): Flow<String> {
        return context.dataStore.data.map {
            it[userToken] ?: ""
        }
    }
}
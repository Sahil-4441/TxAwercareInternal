package com.example.avercare.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.avercare.domain.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object Keys {
        val USER_ID = stringPreferencesKey("user_id")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    suspend fun saveUser(id: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.USER_ID] = id
           /*prefs[Keys.USER_EMAIL] = email
            prefs[Keys.USER_NAME] = name
            prefs[Keys.USER_TOKEN] = token*/
        }
    }

    fun getUser(): Flow<User?> =
        context.dataStore.data.map { prefs ->
            val id = prefs[Keys.USER_ID] ?: return@map null
            val email = prefs[Keys.USER_EMAIL] ?: ""
            val name = prefs[Keys.USER_NAME] ?: ""
            val token = prefs[Keys.USER_TOKEN] ?: ""
            User(id, email, name, token)
        }

    suspend fun clearUser() {
        context.dataStore.edit { it.clear() }
    }
}



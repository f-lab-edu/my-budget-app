package kr.ksw.mybudget.data.repository.user

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : UserRepository {
    override suspend fun setUserName(userName: String) {
        userDataStorePreferences.edit { pref ->
            pref[KEY_NAME] = userName
        }
    }

    override suspend fun getUserName(): Result<String> = runCatching {
        val flow = userDataStorePreferences.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[KEY_NAME]
            }
        flow.firstOrNull() ?: ""
    }

    private companion object {
        val KEY_NAME = stringPreferencesKey(
            name = "name"
        )
    }
}
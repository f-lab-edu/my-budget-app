package kr.ksw.mybudget.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.ksw.mybudget.data.repository.user.UserRepository
import kr.ksw.mybudget.data.repository.user.UserRepositoryImpl
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "kr.ksw.mybudget.user_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {
    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        myUserPreferencesRepository: UserRepositoryImpl
    ): UserRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.userDataStore
        }
    }
}
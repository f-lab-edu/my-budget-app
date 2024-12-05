package kr.ksw.mybudget.data.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.ksw.mybudget.data.local.databases.MyBudgetDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMyBudgetDatabase(application: Application): MyBudgetDatabase {
        return Room.databaseBuilder(
            application,
            MyBudgetDatabase::class.java,
            "my_budget.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSpendingDao(myBudgetDatabase: MyBudgetDatabase) = myBudgetDatabase.spendingDao

    @Provides
    @Singleton
    fun provideCardDao(myBudgetDatabase: MyBudgetDatabase) = myBudgetDatabase.cardDao
}
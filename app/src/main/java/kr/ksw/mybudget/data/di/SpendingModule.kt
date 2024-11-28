package kr.ksw.mybudget.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.ksw.mybudget.data.local.dao.SpendingDao
import kr.ksw.mybudget.data.repository.spending.SpendingRepository
import kr.ksw.mybudget.data.repository.spending.SpendingRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpendingModule {
    @Provides
    @Singleton
    fun provideSpendingRepository(spendingDao: SpendingDao): SpendingRepository {
        return SpendingRepositoryImpl(spendingDao)
    }
}
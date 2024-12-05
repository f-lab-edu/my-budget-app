package kr.ksw.mybudget.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.ksw.mybudget.data.local.dao.CardDao
import kr.ksw.mybudget.data.repository.card.CardRepository
import kr.ksw.mybudget.data.repository.card.CardRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CardModule {
    @Provides
    @Singleton
    fun provideCardRepository(cardDao: CardDao): CardRepository {
        return CardRepositoryImpl(cardDao)
    }
}
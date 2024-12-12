package kr.ksw.mybudget.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kr.ksw.mybudget.domain.usecase.add.card.AddCardUseCase
import kr.ksw.mybudget.domain.usecase.add.card.AddCardUseCaseImpl
import kr.ksw.mybudget.domain.usecase.add.card.GetAllCardUseCase
import kr.ksw.mybudget.domain.usecase.add.card.GetAllCardUseCaseImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class CardModule {
    @Binds
    abstract fun bindAddCardUseCase(
        addCardUseCase: AddCardUseCaseImpl
    ): AddCardUseCase

    @Binds
    abstract fun bindGetAllCardUseCase(
        getAllCardUseCase: GetAllCardUseCaseImpl
    ): GetAllCardUseCase
}
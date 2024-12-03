package kr.ksw.mybudget.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kr.ksw.mybudget.domain.usecase.add.AddSpendingUseCase
import kr.ksw.mybudget.domain.usecase.add.AddSpendingUseCaseImpl
import kr.ksw.mybudget.domain.usecase.home.GetMonthlySpendingUseCase
import kr.ksw.mybudget.domain.usecase.home.GetMonthlySpendingUseCaseImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class SpendingModule {
    @Binds
    abstract fun bindAddSpendingUseCase(
        addSpendingUseCase: AddSpendingUseCaseImpl
    ): AddSpendingUseCase

    @Binds
    abstract fun bindGetMonthlySpendingUseCase(
        getMonthlySpendingUseCase: GetMonthlySpendingUseCaseImpl
    ): GetMonthlySpendingUseCase
}
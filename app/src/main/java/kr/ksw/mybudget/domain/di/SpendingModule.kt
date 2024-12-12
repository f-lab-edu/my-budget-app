package kr.ksw.mybudget.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kr.ksw.mybudget.domain.usecase.add.spending.AddSpendingUseCase
import kr.ksw.mybudget.domain.usecase.add.spending.AddSpendingUseCaseImpl
import kr.ksw.mybudget.domain.usecase.home.GetMonthlySpendingUseCase
import kr.ksw.mybudget.domain.usecase.home.GetMonthlySpendingUseCaseImpl
import kr.ksw.mybudget.domain.usecase.home.GetPreviousMonthSpendingUseCase
import kr.ksw.mybudget.domain.usecase.home.GetPreviousMonthSpendingUseCaseImpl

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

    @Binds
    abstract fun bindGetPreviousMonthSpendingUSeCase(
        getPreviousMonthSpendingUseCase: GetPreviousMonthSpendingUseCaseImpl
    ): GetPreviousMonthSpendingUseCase
}
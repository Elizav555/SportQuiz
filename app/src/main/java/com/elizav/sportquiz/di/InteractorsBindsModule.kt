package com.elizav.sportquiz.di

import com.elizav.sportquiz.domain.interactor.QuizInteractor
import com.elizav.sportquiz.domain.interactor.QuizInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InteractorsBindsModule {
    @Binds
    abstract fun bindQuizInteractor(
        quizInteractorImpl: QuizInteractorImpl
    ): QuizInteractor
}
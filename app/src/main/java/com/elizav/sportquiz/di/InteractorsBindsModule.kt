package com.elizav.sportquiz.di

import com.elizav.sportquiz.domain.interactor.config.ConfigInteractor
import com.elizav.sportquiz.domain.interactor.config.ConfigInteractorImpl
import com.elizav.sportquiz.domain.interactor.quiz.QuizInteractor
import com.elizav.sportquiz.domain.interactor.quiz.QuizInteractorImpl
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

    @Binds
    abstract fun bindConfigInteractor(
        configInteractorImpl: ConfigInteractorImpl
    ): ConfigInteractor
}
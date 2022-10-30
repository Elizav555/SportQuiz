package com.elizav.sportquiz.di

import com.elizav.sportquiz.data.repository.QuizRepositoryImpl
import com.elizav.sportquiz.data.repository.RemoteConfigRepositoryImpl
import com.elizav.sportquiz.domain.repository.QuizRepository
import com.elizav.sportquiz.domain.repository.RemoteConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindsModule {
    @Binds
    @Singleton
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ): QuizRepository

    @Binds
    @Singleton
    abstract fun bindRemoteConfigRepository(
        remoteConfigRepositoryImpl: RemoteConfigRepositoryImpl
    ): RemoteConfigRepository
}
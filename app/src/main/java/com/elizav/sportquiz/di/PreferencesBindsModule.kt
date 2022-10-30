package com.elizav.sportquiz.di

import com.elizav.sportquiz.data.repository.PreferencesRepositoryImpl
import com.elizav.sportquiz.domain.interactor.preferences.PreferencesInteractor
import com.elizav.sportquiz.domain.repository.PreferencesRepository
import com.elizav.sportquiz.data.preferences.PreferencesImpl
import com.elizav.sportquiz.domain.interactor.preferences.PreferencesInteractorImpl
import com.elizav.sportquiz.domain.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesBindsModule {
    @Binds
    abstract fun bindPreferencesInteractor(
        preferencesInteractorImpl: PreferencesInteractorImpl
    ): PreferencesInteractor

    @Binds
    @Singleton
    abstract fun bindPreferences(
        preferencesImpl: PreferencesImpl
    ): Preferences

    @Binds
    @Singleton
    abstract fun bindPreferencesRepository(
        preferencesRepositoryImpl: PreferencesRepositoryImpl
    ): PreferencesRepository
}
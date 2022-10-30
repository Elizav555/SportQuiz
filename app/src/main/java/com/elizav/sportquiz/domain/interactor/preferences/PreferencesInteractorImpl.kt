package com.elizav.sportquiz.domain.interactor.preferences

import com.elizav.sportquiz.domain.repository.PreferencesRepository
import javax.inject.Inject

class PreferencesInteractorImpl @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) : PreferencesInteractor {

    companion object {
        const val PREFS_KEY = "SportQuizKey"
    }

    override suspend fun saveUrl(url: String) = preferencesRepository.saveUrl(PREFS_KEY, url)

    override suspend fun getUrl(): String? = preferencesRepository.getUrl(PREFS_KEY)

    override suspend fun deleteUrl() = preferencesRepository.deleteUrl(PREFS_KEY)
}
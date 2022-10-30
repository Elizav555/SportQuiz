package com.elizav.sportquiz.domain.interactor.preferences

interface PreferencesInteractor {
    suspend fun saveUrl(url: String)
    suspend fun getUrl(): String?
    suspend fun deleteUrl()
}
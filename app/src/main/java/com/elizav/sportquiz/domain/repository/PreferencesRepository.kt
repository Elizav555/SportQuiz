package com.elizav.sportquiz.domain.repository

interface PreferencesRepository {
    suspend fun saveUrl(key: String, url: String)
    suspend fun getUrl(key: String): String?
    suspend fun deleteUrl(key: String)
}
package com.elizav.sportquiz.data.repository

import com.elizav.sportquiz.di.coroutines.IoDispatcher
import com.elizav.sportquiz.domain.repository.PreferencesRepository
import com.elizav.sportquiz.domain.preferences.Preferences
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: Preferences,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : PreferencesRepository {

    override suspend fun saveUrl(key: String, url: String) = withContext(coroutineDispatcher) {
        preferences.setItem(key, url)
    }

    override suspend fun getUrl(key: String): String? = withContext(coroutineDispatcher) {
        preferences.getItem<String>(key, object : TypeToken<String>() {}.type)
    }

    override suspend fun deleteUrl(key: String) = withContext(coroutineDispatcher) {
        preferences.deleteItem<String>(key)
    }
}
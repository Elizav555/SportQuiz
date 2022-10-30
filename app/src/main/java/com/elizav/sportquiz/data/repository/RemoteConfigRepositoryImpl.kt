package com.elizav.sportquiz.data.repository

import com.elizav.sportquiz.domain.model.AppException
import com.elizav.sportquiz.domain.repository.RemoteConfigRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RemoteConfigRepositoryImpl @Inject constructor(private val remoteConfig: FirebaseRemoteConfig) :
    RemoteConfigRepository {
    override suspend fun getUrl() = suspendCoroutine { continuation ->
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(remoteConfig.getString(URL_KEY))
                } else {
                    continuation.resumeWithException(AppException.ConfigException())
                }
            }
    }

    companion object {
        const val URL_KEY = "url"
    }
}
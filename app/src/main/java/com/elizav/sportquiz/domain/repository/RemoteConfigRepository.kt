package com.elizav.sportquiz.domain.repository

interface RemoteConfigRepository {
    suspend fun getUrl(): String
}

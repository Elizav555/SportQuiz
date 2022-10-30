package com.elizav.sportquiz.domain.interactor.config

interface ConfigInteractor {
    suspend fun getUrl(): String
}
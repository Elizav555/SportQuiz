package com.elizav.sportquiz.domain.interactor.config

import com.elizav.sportquiz.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class ConfigInteractorImpl @Inject constructor(
    private val configRepository: RemoteConfigRepository
) : ConfigInteractor {
    override suspend fun getUrl(): String =
        configRepository.getUrl()
}
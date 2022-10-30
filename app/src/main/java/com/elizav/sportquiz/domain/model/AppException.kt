package com.elizav.sportquiz.domain.model

sealed class AppException(message: String) : Exception(message) {
    class ApiException(message: String = API_EX_MSG) : AppException(message)
    class ConfigException(message: String = CONFIG_EX_MSG) : AppException(message)

    companion object {
        const val API_EX_MSG = "Api Error"
        const val CONFIG_EX_MSG = "Remote Config Error"
    }
}

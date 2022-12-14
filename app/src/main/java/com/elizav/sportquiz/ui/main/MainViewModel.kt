package com.elizav.sportquiz.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elizav.sportquiz.domain.interactor.config.ConfigInteractor
import com.elizav.sportquiz.domain.interactor.preferences.PreferencesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesInteractor: PreferencesInteractor,
    private val configInteractor: ConfigInteractor
) : ViewModel() {
    private var _path: MutableLiveData<Result<String>> = MutableLiveData()
    val path: LiveData<Result<String>> = _path

    init {
        getUrl()
    }

    fun getUrl() = viewModelScope.launch {
        try {
            preferencesInteractor.getUrl()?.let {
                _path.value =   Result.success(it)
                return@launch
            }
            _path.value =   Result.success(configInteractor.getUrl())
        } catch (ex: Exception) {
            _path.value = Result.failure(ex)
        }
    }
}
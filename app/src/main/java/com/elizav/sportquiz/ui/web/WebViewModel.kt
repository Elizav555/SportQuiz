package com.elizav.sportquiz.ui.web

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elizav.sportquiz.domain.interactor.preferences.PreferencesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class WebViewModel @Inject constructor(
    private val preferencesInteractor: PreferencesInteractor
) : ViewModel() {
    fun saveUrl(url: String) = viewModelScope.launch {
        preferencesInteractor.saveUrl(url)
    }
}
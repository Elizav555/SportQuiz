package com.elizav.sportquiz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elizav.sportquiz.domain.interactor.QuizInteractor
import com.elizav.sportquiz.domain.model.QuizItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val quizInteractor: QuizInteractor
) : ViewModel() {
    private var _quizItems: MutableLiveData<Result<List<QuizItem>>> = MutableLiveData()
    val quizItems: LiveData<Result<List<QuizItem>>> = _quizItems

    init {
        getQuizItems()
    }

    fun getQuizItems() = viewModelScope.launch {
        quizInteractor.getQuizItems().fold(
            onSuccess = { quizItems ->
                _quizItems.value = Result.success(quizItems)
            },
            onFailure = { _quizItems.value = Result.failure(it) }
        )
    }
}

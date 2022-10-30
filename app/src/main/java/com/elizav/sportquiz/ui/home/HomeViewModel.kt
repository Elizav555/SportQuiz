package com.elizav.sportquiz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elizav.sportquiz.domain.interactor.QuizInteractor
import com.elizav.sportquiz.domain.model.AppException.Companion.API_EX_MSG
import com.elizav.sportquiz.domain.model.Command
import com.elizav.sportquiz.domain.model.QuizItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val quizInteractor: QuizInteractor
) : ViewModel() {
    private var _quizItems: MutableLiveData<List<QuizItem>> = MutableLiveData()
    val quizItems: LiveData<List<QuizItem>> = _quizItems

    private var _currentQuestion: MutableLiveData<Int> = MutableLiveData()
    val currentQuestion: LiveData<Int> = _currentQuestion

    private var _commands: MutableLiveData<Command> = MutableLiveData()
    val commands: LiveData<Command> = _commands

    private var current = 0
    private var score = 0

    init {
        getQuizItems()
    }

    fun getQuizItems() = viewModelScope.launch {
        _commands.value = Command.HandleLoading(true)
        current = 0
        score = 0
        quizInteractor.getQuizItems().fold(
            onSuccess = { quizItems ->
                _quizItems.value = quizItems
                _commands.value = Command.HandleLoading(false)
            },
            onFailure = {
                _commands.value = Command.ShowError(it.message ?: API_EX_MSG)
            }
        )
    }

    fun onAnswer(isCorrect: Boolean) = viewModelScope.launch {
        delay(1000)
        if (isCorrect) {
            score++
        }
        current++
        _quizItems.value?.size?.let {
            if (current >= it) {
                _commands.value = Command.ShowEndGame(score)
            } else _currentQuestion.value = current
        } ?: run { _commands.value = Command.ShowError(API_EX_MSG) }
    }
}

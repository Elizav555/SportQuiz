package com.elizav.sportquiz.domain.model

sealed class Command {
    data class HandleLoading(val isLoading: Boolean) : Command()
    data class ShowEndGame(val score: Int, val lastIsCorrect: Boolean) : Command()
    data class NextQuestion(val isCorrect: Boolean, val newQuestion: Int, val score: Int) :
        Command()

    data class ShowError(val message: String) : Command()
}
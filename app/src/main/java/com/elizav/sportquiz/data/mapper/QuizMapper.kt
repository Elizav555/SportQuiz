package com.elizav.sportquiz.data.mapper

import com.elizav.sportquiz.data.model.QuizItem
import com.elizav.sportquiz.domain.model.Difficulty
import com.elizav.sportquiz.domain.model.QuizItem as QuizItemDomain

object QuizItemMapper {
    fun QuizItem.toDomain() = QuizItemDomain(
        id = id.toLong(),
        difficulty = Difficulty.values().firstOrNull { it.name == difficulty } ?: Difficulty.medium,
        question = question,
        correctAnswer = correct_answer,
        incorrectAnswers = incorrect_answers
    )
}
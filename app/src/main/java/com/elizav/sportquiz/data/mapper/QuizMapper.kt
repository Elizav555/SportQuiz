package com.elizav.sportquiz.data.mapper

import com.elizav.sportquiz.data.model.QuizItem
import com.elizav.sportquiz.domain.model.QuizItem as QuizItemDomain

object QuizItemMapper {
    fun QuizItem.toDomain() = QuizItemDomain(
        id = id,
        difficulty = difficulty,
        question = question,
        correct_answer = correct_answer,
        incorrect_answers = incorrect_answers
    )
}
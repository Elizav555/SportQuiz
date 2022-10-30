package com.elizav.sportquiz.domain.model

data class QuizItem(
    val id: Int,
    val difficulty: Difficulty,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>,
)
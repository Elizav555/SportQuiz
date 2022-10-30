package com.elizav.sportquiz.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizItem(
    @SerialName("id") val id: Int,
    @SerialName("type") val type: String,
    @SerialName("category") val category: String,
    @SerialName("difficulty") val difficulty: String,
    @SerialName("question") val question: String,
    @SerialName("correct_answer") val correct_answer: String,
    @SerialName("incorrect_answers") val incorrect_answers: List<String>,
)
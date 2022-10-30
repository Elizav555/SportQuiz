package com.elizav.sportquiz.domain.model

import androidx.recyclerview.widget.DiffUtil

data class QuizItem(
    val id: Long,
    val difficulty: Difficulty,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
) {
    class DiffCallback : DiffUtil.ItemCallback<QuizItem>() {
        override fun areItemsTheSame(oldItem: QuizItem, newItem: QuizItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuizItem, newItem: QuizItem): Boolean {
            return oldItem.question == newItem.question && oldItem.incorrectAnswers == newItem.incorrectAnswers
        }

    }
}
package com.elizav.sportquiz.domain.repository

import com.elizav.sportquiz.domain.model.QuizItem

interface QuizRepository {
    suspend fun getQuizItems(
        category: String,
        type: String,
        limit: Int,
    ): Result<List<QuizItem>>
}
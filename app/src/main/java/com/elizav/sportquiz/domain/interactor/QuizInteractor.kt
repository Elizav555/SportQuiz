package com.elizav.sportquiz.domain.interactor

import com.elizav.sportquiz.domain.model.Category
import com.elizav.sportquiz.domain.model.QuizItem
import com.elizav.sportquiz.domain.model.Type

interface QuizInteractor {
    suspend fun getQuizItems(
        category: Category = Category.Sports,
        type: Type = Type.multiple,
        limit: Int = 10
    ): Result<List<QuizItem>>
}
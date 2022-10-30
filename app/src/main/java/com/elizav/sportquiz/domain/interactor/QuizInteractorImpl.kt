package com.elizav.sportquiz.domain.interactor

import com.elizav.sportquiz.domain.model.Category
import com.elizav.sportquiz.domain.model.QuizItem
import com.elizav.sportquiz.domain.model.Type
import com.elizav.sportquiz.domain.repository.QuizRepository
import javax.inject.Inject

class QuizInteractorImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : QuizInteractor {
    override suspend fun getQuizItems(
        category: Category,
        type: Type,
        limit: Int
    ): Result<List<QuizItem>> = quizRepository.getQuizItems(
        category.name, type.name, limit
    )
}
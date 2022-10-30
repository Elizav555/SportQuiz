package com.elizav.sportquiz.data.repository

import com.elizav.sportquiz.data.api.QuizApi
import com.elizav.sportquiz.data.mapper.QuizItemMapper.toDomain
import com.elizav.sportquiz.di.coroutines.IoDispatcher
import com.elizav.sportquiz.domain.model.AppException
import com.elizav.sportquiz.domain.model.QuizItem
import com.elizav.sportquiz.domain.repository.QuizRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class QuizRepositoryImpl @Inject constructor(
    private val quizApi: QuizApi,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : QuizRepository {
    override suspend fun getQuizItems(
        category: String,
        type: String,
        limit: Int,
    ): Result<List<QuizItem>> = withContext(coroutineDispatcher) {
        val correctLimit = when (limit) {
            in MAX_LIMIT..Int.MAX_VALUE -> MAX_LIMIT
            in Int.MIN_VALUE..MIN_LIMIT -> MIN_LIMIT
            else -> limit
        }
        try {
            with(
                quizApi.getQuizItems(
                    category, type, correctLimit
                )
            ) {
                if (isSuccessful) {
                    Result.success(body()?.map { it.toDomain() } ?: listOf())
                } else Result.failure(AppException.ApiException())
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    companion object {
        const val MAX_LIMIT = 10
        const val MIN_LIMIT = 1
    }
}
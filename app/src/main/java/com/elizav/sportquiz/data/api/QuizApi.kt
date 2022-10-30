package com.elizav.sportquiz.data.api

import com.elizav.sportquiz.data.model.QuizItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://beta-trivia.bongo.best/?category=entertainment&type=multiple&limit=10
interface QuizApi {
    @GET("/?")
    suspend fun getQuizItems(
        @Query("category") category: String,
        @Query("type") type: String,
        @Query("limit") limit: Int,
    ): Response<List<QuizItem>>
}
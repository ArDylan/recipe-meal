package com.dylan.recipemeal.data.api

import com.dylan.recipemeal.data.model.MealsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("search.php")
    suspend fun getMeals(
        @Query("s") search: String
    ): Response<MealsResponse>

    @GET("lookup.php")
    suspend fun getMealDetail(
        @Query("i") id: String
    ): Response<MealsResponse>
}
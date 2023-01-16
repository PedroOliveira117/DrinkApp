package com.example.drinkapp.data.remote

import com.example.drinkapp.common.ApiConstants
import com.example.drinkapp.common.ApiConstants.API_QUERY_PAGE
import com.example.drinkapp.common.ApiConstants.API_QUERY_PER_PAGE
import com.example.drinkapp.data.models.DrinkDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */
interface DrinksApi {

    @GET(ApiConstants.API_GET_DRINKS)
    suspend fun getDrinks(@Query(API_QUERY_PAGE) page: Int, @Query(API_QUERY_PER_PAGE) perPage: Int): List<DrinkDto>
}
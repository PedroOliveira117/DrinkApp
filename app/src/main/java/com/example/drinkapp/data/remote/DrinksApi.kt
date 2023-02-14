package com.example.drinkapp.data.remote

import com.example.drinkapp.common.ApiConstants.API_GET_DRINKS
import com.example.drinkapp.common.ApiConstants.API_QUERY_BEER_ID
import com.example.drinkapp.common.ApiConstants.API_QUERY_BEER_NAME
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

    @GET(API_GET_DRINKS)
    suspend fun getDrinks(@Query(API_QUERY_PAGE) page: Int, @Query(API_QUERY_PER_PAGE) perPage: Int): List<DrinkDto>

    @GET(API_GET_DRINKS)
    suspend fun getDrinkById(@Query(API_QUERY_BEER_ID) drinkId: String): List<DrinkDto>

    @GET(API_GET_DRINKS)
    suspend fun searchDrink(@Query(API_QUERY_BEER_NAME) keyword: String, @Query(API_QUERY_PAGE) page: Int, @Query(API_QUERY_PER_PAGE) perPage: Int): List<DrinkDto>
}
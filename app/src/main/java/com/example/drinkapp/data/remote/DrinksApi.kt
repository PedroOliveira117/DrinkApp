package com.example.drinkapp.data.remote

import com.example.drinkapp.common.ApiConstants
import com.example.drinkapp.domain.models.Drink
import retrofit2.http.GET

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */
interface DrinksApi {

    @GET(ApiConstants.API_GET_BEERS)
    suspend fun getBeers(): ArrayList<Drink>
}
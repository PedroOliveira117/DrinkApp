package com.example.drinkapp.domain.repository

import com.example.drinkapp.common.Resource
import com.example.drinkapp.data.models.DrinkFavDto
import com.example.drinkapp.domain.models.Drink
import kotlinx.coroutines.flow.Flow

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */
interface IDrinksRepository {

    suspend fun getDrinks(page: Int, perPage: Int): Resource<List<Drink>>

    suspend fun getDrinkById(id: String): Resource<Drink>

    suspend fun searchDrink(keyword: String, page: Int, perPage: Int): Resource<List<Drink>>

    suspend fun insertFav(id: String)

    suspend fun removeFav(id: String)

    suspend fun getFavList(): Flow<List<DrinkFavDto>>
}
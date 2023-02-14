package com.example.drinkapp.data.local

import androidx.room.*
import com.example.drinkapp.data.models.DrinkDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by pedrooliveira on 12/01/2023
 * All rights reserved GoodBarber
 */
@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_database ORDER BY CAST(id as INT) ASC LIMIT :perPage OFFSET :page * :perPage")
    suspend fun getDrinks(page: Int, perPage: Int): List<DrinkDto>

    @Query("SELECT * FROM drink_database WHERE id LIKE '%' || :drinkId || '%'")
    suspend fun getDrinkById(drinkId: String): List<DrinkDto>

    @Query("SELECT * FROM drink_database WHERE id in (:drinkIds)")
    suspend fun getDrinkByIds(drinkIds: List<String>): List<DrinkDto>

    @Query("SELECT * FROM drink_database WHERE name LIKE '%' || :keyword || '%' ORDER BY CAST(id as INT) ASC LIMIT :perPage OFFSET :page * :perPage")
    suspend fun searchDrink(keyword: String, page: Int, perPage: Int): List<DrinkDto>

    @Query("SELECT drink_database.* FROM drink_database INNER JOIN drink_fav_database ON drink_fav_database.id = drink_database.id ORDER BY CAST(drink_fav_database.added_at as LONG) DESC")
    fun getFavoriteDrinks(): Flow<List<DrinkDto>>

    @Upsert
    suspend fun insertDrink(drink: List<DrinkDto>)

    @Delete
    suspend fun removeDrinks(drinkList: List<DrinkDto>)
}
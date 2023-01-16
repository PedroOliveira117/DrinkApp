package com.example.drinkapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.data.models.DrinkDto
import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 12/01/2023
 * All rights reserved GoodBarber
 */
@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_database ORDER BY CAST(id as INT) ASC LIMIT :perPage OFFSET :page * :perPage")
    suspend fun getBeers(page: Int, perPage: Int): List<DrinkDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrink(drink: List<DrinkDto>)
}
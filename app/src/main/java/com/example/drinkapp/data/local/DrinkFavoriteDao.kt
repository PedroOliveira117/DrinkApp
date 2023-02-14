package com.example.drinkapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.data.models.DrinkFavoriteDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by pedrooliveira on 08/02/2023
 * All rights reserved GoodBarber
 */
@Dao
interface DrinkFavoriteDao {

    @Query("SELECT * FROM drink_fav_database")
    fun getFavoriteList(): Flow<List<DrinkFavoriteDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink: DrinkFavoriteDto)

    @Delete
    suspend fun removeFavorite(drink: DrinkFavoriteDto)
}
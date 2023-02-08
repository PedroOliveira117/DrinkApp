package com.example.drinkapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.data.models.DrinkFavDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by pedrooliveira on 08/02/2023
 * All rights reserved GoodBarber
 */
@Dao
interface DrinkFavDao {

    @Query("SELECT * FROM drink_fav_database")
    fun getFavList(): Flow<List<DrinkFavDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(drink: DrinkFavDto)

    @Delete
    suspend fun removeFav(drink: DrinkFavDto)
}
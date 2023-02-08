package com.example.drinkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinkapp.data.models.Converters
import com.example.drinkapp.data.models.DrinkDto
import com.example.drinkapp.data.models.DrinkFavDto

/**
 * Created by pedrooliveira on 12/01/2023
 * All rights reserved GoodBarber
 */

@Database(
    entities = [DrinkDto::class, DrinkFavDto::class],
    version = 4
)
@TypeConverters(Converters::class)

abstract class DrinksDataBase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
    abstract fun drinkFavDao(): DrinkFavDao
}
package com.example.drinkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinkapp.data.models.Converters
import com.example.drinkapp.data.models.DrinkDto

/**
 * Created by pedrooliveira on 12/01/2023
 * All rights reserved GoodBarber
 */

@Database(
    entities = [DrinkDto::class],
    version = 3
)
@TypeConverters(Converters::class)

abstract class DrinksDataBase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
}
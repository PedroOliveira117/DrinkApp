package com.example.drinkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 12/01/2023
 * All rights reserved GoodBarber
 */

@Database(
    entities = [Drink::class],
    version = 1
)

abstract class DrinksDataBase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
}
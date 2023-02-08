package com.example.drinkapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pedrooliveira on 08/02/2023
 * All rights reserved GoodBarber
 */
@Entity(tableName = "drink_fav_database")
data class DrinkFavDto(
    @PrimaryKey
    val id: String
)
package com.example.drinkapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */

@Entity(tableName = "drink_database")
data class Drink(
    @PrimaryKey
    val id: String,
    val name: String,
    @SerializedName("image_url")
    val thumbUrl: String?,
    @SerializedName("tagline")
    val tag: String,
    val description: String,
    @SerializedName("first_brewed")
    val brewedDate: String,
    @SerializedName("brewers_tips")
    val brewersTips: String,
    val ph: Float
)

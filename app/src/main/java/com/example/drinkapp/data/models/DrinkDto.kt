package com.example.drinkapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.domain.models.Drink
import com.google.gson.annotations.SerializedName

/**
 * Created by pedrooliveira on 16/01/2023
 * All rights reserved GoodBarber
 */
@Entity(tableName = "drink_database")
data class DrinkDto(
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

fun DrinkDto.toDrink(): Drink {
    return Drink(
        id = id,
        name = name,
        thumbUrl = thumbUrl,
        tag = tag,
        description = description,
        brewedDate = brewedDate,
        brewersTips = brewersTips,
        ph = ph
    )
}
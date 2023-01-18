package com.example.drinkapp.data.models

import androidx.room.*
import com.example.drinkapp.domain.models.Drink
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Created by pedrooliveira on 16/01/2023
 * All rights reserved GoodBarber
 */
@Entity(tableName = "drink_database")
@TypeConverters(Converters::class)
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
    val ph: Float,
    @Embedded
    val volume: MeasureDto,
    @SerializedName("food_pairing")
    @ColumnInfo(name = "food_pairing")
    val foodMatches: List<String>
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
        ph = ph,
        volume = volume.toMeasure(),
        foodMatches = foodMatches
    )
}

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}
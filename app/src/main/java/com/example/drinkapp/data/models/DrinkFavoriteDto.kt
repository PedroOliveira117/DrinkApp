package com.example.drinkapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

/**
 * Created by pedrooliveira on 08/02/2023
 * All rights reserved GoodBarber
 */
@Entity(
    tableName = "drink_fav_database",
    foreignKeys = [ForeignKey(
        entity = DrinkDto::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class DrinkFavoriteDto(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "added_at")
    val addedAt: Long? = null
)
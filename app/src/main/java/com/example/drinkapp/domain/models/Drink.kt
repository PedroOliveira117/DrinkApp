package com.example.drinkapp.domain.models

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */

data class Drink(
    val id: String,
    val name: String,
    val thumbUrl: String?,
    val tag: String,
    val description: String,
    val brewedDate: String,
    val brewersTips: String,
    val ph: Float,
    val volume: Measure,
    val foodMatches: List<String> = emptyList()
)

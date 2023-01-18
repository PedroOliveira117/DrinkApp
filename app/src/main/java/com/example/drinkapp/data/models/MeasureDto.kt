package com.example.drinkapp.data.models

import com.example.drinkapp.domain.models.Measure

/**
 * Created by pedrooliveira on 18/01/2023
 * All rights reserved GoodBarber
 */
data class MeasureDto(
    val value: Int,
    val unit: String
)

fun MeasureDto.toMeasure(): Measure {
    return Measure(
        value = value,
        unit = unit
    )
}
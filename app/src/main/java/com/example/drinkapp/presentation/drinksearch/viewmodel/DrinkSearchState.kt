package com.example.drinkapp.presentation.drinksearch.viewmodel

import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 16/01/2023
 * All rights reserved GoodBarber
 */
data class DrinkSearchState(
    val drinksList: List<Drink> = emptyList(),
    val keyword: String = "",
    val isLoading: Boolean = false,
    var page: Int = 1,
)
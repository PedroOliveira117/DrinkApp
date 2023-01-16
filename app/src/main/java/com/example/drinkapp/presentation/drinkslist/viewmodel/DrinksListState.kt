package com.example.drinkapp.presentation.drinkslist.viewmodel

import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
data class DrinksListState(
    val drinksList: List<Drink> = emptyList(),
    val isLoading: Boolean = false,
    var page: Int = 1,
)
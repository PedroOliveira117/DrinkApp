package com.example.drinkapp.presentation.drinkfavorites.viewmodel

import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 09/02/2023
 * All rights reserved GoodBarber
 */
data class DrinkFavoriteState(
    val drinksList: List<Drink> = emptyList(),
    val isLoading: Boolean = false,
)
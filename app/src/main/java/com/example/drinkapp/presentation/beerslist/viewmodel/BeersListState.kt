package com.example.drinkapp.presentation.beerslist.viewmodel

import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
data class BeersListState(
    val beersList: List<Drink> = emptyList(),
    val isLoading: Boolean = false,
    var page: Int = 1,
)
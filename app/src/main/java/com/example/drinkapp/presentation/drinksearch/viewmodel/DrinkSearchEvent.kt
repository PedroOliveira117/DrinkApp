package com.example.drinkapp.presentation.drinksearch.viewmodel

/**
 * Created by pedrooliveira on 16/01/2023
 * All rights reserved GoodBarber
 */
sealed class DrinkSearchEvent {
    data class NewSearchEvent(val keyword: String) : DrinkSearchEvent()
    object NextPageEvent : DrinkSearchEvent()
    object ClearSearchEvent : DrinkSearchEvent()
    data class UpdateFavEvent(val id: String): DrinkSearchEvent()
}

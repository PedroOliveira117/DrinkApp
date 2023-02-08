package com.example.drinkapp.presentation.drinkslist.viewmodel

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
sealed class DrinksListEvent {
    object LoadDrinksEvent: DrinksListEvent()
    object NextPageEvent: DrinksListEvent()
    data class UpdateFavEvent(val id: String): DrinksListEvent()
}

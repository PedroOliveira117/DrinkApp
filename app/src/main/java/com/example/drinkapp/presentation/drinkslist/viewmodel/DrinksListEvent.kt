package com.example.drinkapp.presentation.drinkslist.viewmodel

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
sealed class DrinksListEvent {
    object NewSearchEvent: DrinksListEvent()
    object NextPageEvent: DrinksListEvent()
}

package com.example.drinkapp.presentation.beerslist.viewmodel

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
sealed class BeersListEvent {
    object NewSearchEvent: BeersListEvent()
    object NextPageEvent: BeersListEvent()
}

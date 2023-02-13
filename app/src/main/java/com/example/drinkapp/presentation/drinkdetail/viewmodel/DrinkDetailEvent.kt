package com.example.drinkapp.presentation.drinkdetail.viewmodel

/**
 * Created by pedrooliveira on 13/02/2023
 * All rights reserved GoodBarber
 */
sealed class DrinkDetailEvent {
    object LoadDrinkEvent: DrinkDetailEvent()
    object UpdateFavoriteEvent: DrinkDetailEvent()
}

package com.example.drinkapp.presentation.drinkfavorites.viewmodel

/**
 * Created by pedrooliveira on 09/02/2023
 * All rights reserved GoodBarber
 */
sealed class DrinkFavoriteEvent {
    data class LoadFavoriteEvent(val ids: List<String>): DrinkFavoriteEvent()
    data class RemoveFavoriteEvent(val id: String): DrinkFavoriteEvent()
}
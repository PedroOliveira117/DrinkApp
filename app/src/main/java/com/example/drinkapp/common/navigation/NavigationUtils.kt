package com.example.drinkapp.common.navigation

import androidx.navigation.NavController

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
object NavigationUtils {

    const val DRINKS_LIST_SCREEN = "drinks_list_screen"
    const val SEARCH_DRINKS_LIST_SCREEN = "drinks_search_screen"
    const val FAVORITE_DRINKS_SCREEN = "drinks_favorite_screen"
    const val DRINK_DETAIL_SCREEN = "drink_detail_screen"

    fun NavController.navigateToDrinkDetail(drinkId: String) {
        navigate("$DRINK_DETAIL_SCREEN/$drinkId")
    }
}
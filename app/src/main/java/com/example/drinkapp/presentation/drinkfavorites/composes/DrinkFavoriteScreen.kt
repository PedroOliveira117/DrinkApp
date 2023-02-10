package com.example.drinkapp.presentation.drinkfavorites.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.drinkapp.common.composes.LoadingIndicator
import com.example.drinkapp.presentation.drinkfavorites.viewmodel.DrinkFavoriteEvent
import com.example.drinkapp.presentation.drinkfavorites.viewmodel.DrinkFavoriteViewModel
import com.example.drinkapp.presentation.drinksearch.composes.DrinkSearchItem
import com.example.drinkapp.ui.theme.gray_200

/**
 * Created by pedrooliveira on 09/02/2023
 * All rights reserved GoodBarber
 */
@Composable
fun DrinkFavoriteScreen (
    navController: NavController,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    viewModel: DrinkFavoriteViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = gray_200)
            .statusBarsPadding()
    ) {
        if (viewModel.state.isLoading) {
            LoadingIndicator(Modifier.fillMaxSize())
        } else {
            LazyColumn(contentPadding = contentPadding) {
                items(state.drinksList.size) { index ->
                    //TODO replace with correct cell
                    DrinkSearchItem(
                        navController = navController,
                        drink = state.drinksList[index],
                        isFavorite = true,
                        onFavClick = {
                            viewModel.onTriggerEvent(DrinkFavoriteEvent.RemoveFavoriteEvent(state.drinksList[index].id))
                        }
                    )
                }
            }
        }
    }
}
package com.example.drinkapp.presentation.drinkdetail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.repository.IDrinksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by pedrooliveira on 17/01/2023
 * All rights reserved GoodBarber
 */
@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val repository: IDrinksRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(DrinkDetailState())

    init {
        onTriggerEvent(DrinkDetailEvent.LoadDrinkEvent)
    }

    fun onTriggerEvent(event: DrinkDetailEvent) {
        when (event) {
            DrinkDetailEvent.LoadDrinkEvent -> getDrinkById(savedStateHandle.get<String>("drinkId"))
            is DrinkDetailEvent.UpdateFavoriteEvent -> updateFavorite()
        }
    }

    private fun getDrinkById(id: String?) {
        state = state.copy(isLoading = true)
        id?.let { drinkId ->
            viewModelScope.launch {
                val response = repository.getDrinkById(id = drinkId)
                response.data?.let { drink ->
                    // Check If Drink is favorite
                    repository.getFavoriteDrinks().take(1).collect { favoriteList ->
                        state = state.copy(
                            drink = drink,
                            isFavorite = favoriteList.contains(drink),
                            isLoading = false
                        )
                    }
                } ?: kotlin.run {
                    if (state.isLoading) {
                        state = state.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun updateFavorite() {
        viewModelScope.launch {
            state.drink?.let { drink ->
                state = if (state.isFavorite) {
                    repository.removeFavorite(drink.id)
                    state.copy(isFavorite = false)
                } else {
                    repository.insertFavorite(drink.id)
                    state.copy(isFavorite = true)
                }
            }
        }
    }
}
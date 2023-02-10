package com.example.drinkapp.presentation.drinkfavorites.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.repository.IDrinksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by pedrooliveira on 09/02/2023
 * All rights reserved GoodBarber
 */
@HiltViewModel
class DrinkFavoriteViewModel @Inject constructor(private val repository: IDrinksRepository) : ViewModel() {

    var state by mutableStateOf(DrinkFavoriteState())

    init {
        // Start Collecting Favorites
        viewModelScope.launch {
            repository.getFavoriteDrinks().collect { list ->
                // If list is empty load favorites from API
                if (state.drinksList.isEmpty()) {
                    onTriggerEvent(
                        DrinkFavoriteEvent.LoadFavoriteEvent(list.map { it.id })
                    )
                } else {
                    state = state.copy(drinksList = list)
                }
            }
        }
    }

    fun onTriggerEvent(event: DrinkFavoriteEvent) {
        when (event) {
            is DrinkFavoriteEvent.LoadFavoriteEvent -> getDrinksByIds(ids = event.ids)
            is DrinkFavoriteEvent.RemoveFavoriteEvent -> removeFavorite(id = event.id)
        }
    }

    private fun getDrinksByIds(ids: List<String>) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            repository.getDrinkByIds(ids).data?.let { list ->
                when {
                    list.isNotEmpty() -> {
                        state = state.copy(
                            drinksList = list,
                            isLoading = false
                        )
                    }
                    state.isLoading -> state = state.copy(isLoading = false)
                }
            } ?: kotlin.run {
                if (state.isLoading) {
                    state = state.copy(isLoading = false)
                }
            }
        }
    }

    private fun removeFavorite(id: String) {
        viewModelScope.launch {
            repository.removeFavorite(id)
        }
    }
}
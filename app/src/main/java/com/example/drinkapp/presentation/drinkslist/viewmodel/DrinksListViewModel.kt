package com.example.drinkapp.presentation.drinkslist.viewmodel

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
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */

@HiltViewModel
class DrinksListViewModel @Inject constructor(private val repository: IDrinksRepository) : ViewModel() {

    var state by mutableStateOf(DrinksListState())
    private val perPage = 24

    init {
        onTriggerEvent(DrinksListEvent.LoadDrinksEvent)

        // Start Collecting Favorites
        viewModelScope.launch {
            repository.getFavoriteDrinks().collect { list ->
                state = state.copy(favList = list.map { it.id })
            }
        }
    }

    fun onTriggerEvent(event: DrinksListEvent) {
        when (event) {
            DrinksListEvent.LoadDrinksEvent -> getDrinks()
            DrinksListEvent.NextPageEvent -> getDrinks(state.page)
            is DrinksListEvent.UpdateFavEvent -> updateFavorites(event.id)
        }
    }

    private fun getDrinks(page: Int = 1) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val response = repository.getDrinks(page, perPage)
            response.data?.let { list ->
                when {
                    list.isNotEmpty() -> {
                        state = state.copy(drinksList = state.drinksList + list, page = state.page + 1)
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

    private fun updateFavorites(id: String) {
        viewModelScope.launch {
            if (state.favList.contains(id)) {
                repository.removeFavorite(id)
            } else {
                repository.insertFavorite(id)
            }
        }
    }

    fun isDrinkFavorite(id: String) = state.favList.contains(id)
}
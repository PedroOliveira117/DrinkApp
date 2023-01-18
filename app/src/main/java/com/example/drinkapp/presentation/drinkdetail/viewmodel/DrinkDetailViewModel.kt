package com.example.drinkapp.presentation.drinkdetail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.repository.IDrinksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
        getDrinkById(savedStateHandle.get<String>("drinkId"))
    }

    private fun getDrinkById(id: String?) {
        id?.let { drinkId ->
            viewModelScope.launch {
                val response = repository.getDrinkById(id = drinkId)
                response.data?.let { drink ->
                    state = state.copy(drink = drink)
                }
            }
        }
    }
}
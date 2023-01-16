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

    var drinksListState by mutableStateOf(DrinksListState())
    private val perPage = 24

    init {
        onTriggerEvent(DrinksListEvent.NewSearchEvent)
    }

    fun onTriggerEvent(event: DrinksListEvent) {
        when (event) {
            DrinksListEvent.NewSearchEvent -> getDrinks()
            DrinksListEvent.NextPageEvent -> getDrinks(drinksListState.page)
        }
    }

    private fun getDrinks(page: Int = 1) {
        drinksListState = drinksListState.copy(isLoading = true)
        viewModelScope.launch {
            val response = repository.getDrinks(page, perPage)
            response.data?.let { list ->
                when {
                    list.isNotEmpty() -> {
                        drinksListState = drinksListState.copy(drinksList = drinksListState.drinksList + list, page = drinksListState.page + 1)
                    }
                    drinksListState.isLoading -> drinksListState = drinksListState.copy(isLoading = false)
                }
            } ?: kotlin.run {
                if (drinksListState.isLoading) {
                    drinksListState = drinksListState.copy(isLoading = false)
                }
            }
        }
    }
}
package com.example.drinkapp.presentation.drinksearch.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.repository.IDrinksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by pedrooliveira on 16/01/2023
 * All rights reserved GoodBarber
 */
@HiltViewModel
class DrinkSearchViewModel @Inject constructor(private val drinksRepository: IDrinksRepository) : ViewModel() {

    var state by mutableStateOf(DrinkSearchState())
    private var searchJob: Job? = null
    private val perPage = 24

    fun onTriggerEvent(event: DrinkSearchEvent) {
        when (event) {
            is DrinkSearchEvent.NewSearchEvent -> {
                // If keyword empty or blank clear state
                if (event.keyword.isEmpty() || event.keyword.isBlank()) {
                    onTriggerEvent(DrinkSearchEvent.ClearSearchEvent)
                } else {
                    // Each time new search request we cancel previous job
                    state = DrinkSearchState(keyword = event.keyword, isLoading = true)
                    searchJob?.cancel()
                    searchJob = viewModelScope.launch {
                        // Delay to start making search request
                        delay(500)
                        searchDrink(event.keyword)
                    }
                }
            }
            is DrinkSearchEvent.NextPageEvent -> {
                // Only search next page if current list has at least 24 items
                if (state.drinksList.size >= perPage) {
                    state = state.copy(isLoading = true)
                    searchDrink(keyword = state.keyword, page = state.page)
                }
            }
            is DrinkSearchEvent.ClearSearchEvent -> {
                searchJob?.cancel()
                state = DrinkSearchState()
            }
        }
    }

    private fun searchDrink(keyword: String, page: Int = 1) {
        viewModelScope.launch {
            val response = drinksRepository.searchDrink(keyword = keyword, page = page, perPage = perPage)
            response.data?.let { list ->
                when {
                    list.isNotEmpty() -> {
                        state = state.copy(drinksList = state.drinksList + list, page = state.page + 1, isLoading = false)
                    }
                    state.isLoading -> {
                        state = state.copy(isLoading = false)
                    }
                }
            } ?: kotlin.run {
                if (state.isLoading) {
                    state = state.copy(isLoading = false)
                }
            }
        }
    }
}
package com.example.drinkapp.presentation.beerslist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.data.repository.DrinksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */

@HiltViewModel
class BeersListViewModel @Inject constructor(private val repository: DrinksRepository) : ViewModel() {

    var beersListState by mutableStateOf(BeersListState())
    private val perPage = 24

    init {
        onTriggerEvent(BeersListEvent.NewSearchEvent)
    }

    fun onTriggerEvent(event: BeersListEvent) {
        when (event) {
            BeersListEvent.NewSearchEvent -> getBeers()
            BeersListEvent.NextPageEvent -> getBeers(beersListState.page)
        }
    }

    private fun getBeers(page: Int = 1) {
        beersListState = beersListState.copy(isLoading = true)
        viewModelScope.launch {
            val response = repository.getBeers(page, perPage)
            response.data?.let { list ->
                when {
                    list.isNotEmpty() -> {
                        beersListState = beersListState.copy(beersList = beersListState.beersList + list, page = beersListState.page + 1)
                    }
                    beersListState.isLoading -> beersListState = beersListState.copy(isLoading = false)
                }
            } ?: kotlin.run {
                if (beersListState.isLoading) {
                    beersListState = beersListState.copy(isLoading = false)
                }
            }
        }
    }
}
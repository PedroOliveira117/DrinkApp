package com.example.drinkapp.presentation.beerslist.composes

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.drinkapp.common.composes.LoadingIndicator
import com.example.drinkapp.presentation.beerslist.viewmodel.BeersListEvent
import com.example.drinkapp.presentation.beerslist.viewmodel.BeersListViewModel

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BeersListScreen(
    modifier: Modifier = Modifier,
    viewModel: BeersListViewModel = hiltViewModel()
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        if (viewModel.beersListState.isLoading && viewModel.beersListState.beersList.isEmpty()) {
            LoadingIndicator(Modifier.fillMaxSize())
        } else {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
            ) {
                val itemsCount = viewModel.beersListState.beersList.size
                viewModel.beersListState.apply {
                    items(itemsCount) { index ->
                        // When reaching last Item Trigger next Page Event
                        if (index >= (itemsCount - 1)) {
                            LaunchedEffect(key1 = true) {
                                viewModel.onTriggerEvent(BeersListEvent.NextPageEvent)
                            }
                        }
                        BeersListItem(beersList[index])
                    }
                    // If is Loading Display loading at bottom for load more
                    if (isLoading) {
                        if (itemsCount % 2 != 0) {
                            item {
                                Box {}
                            }
                        }
                        item(span = { GridItemSpan(2) }) {
                            LoadingIndicator(Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }
}
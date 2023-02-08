package com.example.drinkapp.presentation.drinkslist.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.drinkapp.common.composes.LoadingIndicator
import com.example.drinkapp.presentation.drinkslist.viewmodel.DrinksListEvent
import com.example.drinkapp.presentation.drinkslist.viewmodel.DrinksListViewModel
import com.example.drinkapp.ui.theme.gray_200

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */

@Composable
fun DrinksListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    viewModel: DrinksListViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = gray_200)
            .statusBarsPadding()
    ) {
        if (viewModel.state.isLoading && viewModel.state.drinksList.isEmpty()) {
            LoadingIndicator(Modifier.fillMaxSize())
        } else {
            LazyVerticalGrid(
                contentPadding = contentPadding,
                columns = GridCells.Fixed(2),
            ) {
                val itemsCount = viewModel.state.drinksList.size
                viewModel.state.apply {
                    items(itemsCount) { index ->
                        // When reaching last Item Trigger next Page Event
                        if (index >= (itemsCount - 1)) {
                            LaunchedEffect(key1 = true) {
                                viewModel.onTriggerEvent(DrinksListEvent.NextPageEvent)
                            }
                        }
                        DrinkListItem(
                            navController = navController,
                            drink = drinksList[index]
                        )
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
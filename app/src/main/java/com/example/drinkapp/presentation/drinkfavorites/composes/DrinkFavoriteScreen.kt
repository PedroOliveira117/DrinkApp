package com.example.drinkapp.presentation.drinkfavorites.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.drinkapp.common.composes.LoadingIndicator
import com.example.drinkapp.presentation.drinkfavorites.viewmodel.DrinkFavoriteEvent
import com.example.drinkapp.presentation.drinkfavorites.viewmodel.DrinkFavoriteViewModel
import com.example.drinkapp.common.composes.DrinkListItem
import com.example.drinkapp.ui.theme.Poppins
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
        when {
            viewModel.state.isLoading -> LoadingIndicator(Modifier.fillMaxSize())
            viewModel.state.drinksList.isEmpty() -> FavoriteEmptyScreen()
            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(top = 10.dp, start = 10.dp, end = 10.dp, bottom = contentPadding.calculateBottomPadding() + 10.dp)
                ) {
                    items(state.drinksList.size) { index ->
                        DrinkListItem(
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
}

@Composable
fun FavoriteEmptyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = Icons.Default.Favorite,
            tint = Color.Black,
            contentDescription = "Favorite Icon"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "You don't have any favorite drinks",
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}
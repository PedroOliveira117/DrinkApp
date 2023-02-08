package com.example.drinkapp.presentation.drinksearch.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.drinkapp.common.composes.LoadingIndicator
import com.example.drinkapp.presentation.drinksearch.viewmodel.DrinkSearchEvent
import com.example.drinkapp.presentation.drinksearch.viewmodel.DrinkSearchViewModel
import com.example.drinkapp.ui.theme.*

/**
 * Created by pedrooliveira on 16/01/2023
 * All rights reserved GoodBarber
 */
@Composable
fun DrinkSearchScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    viewModel: DrinkSearchViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = gray_200)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            OutlinedTextField(
                value = state.keyword,
                onValueChange = {
                    viewModel.onTriggerEvent(DrinkSearchEvent.NewSearchEvent(keyword = it))
                },
                placeholder = {
                    Text(
                        "Search your beer",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = Color.Black,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (state.keyword.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = Color.Black,
                            contentDescription = "Clear Icon",
                            modifier = Modifier.clickable {
                                viewModel.onTriggerEvent(DrinkSearchEvent.ClearSearchEvent)
                            }
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    backgroundColor = gray_400
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            when {
                state.keyword.isEmpty() -> {
                    SearchMessageView(message = "Start searching")
                }
                state.isLoading && state.page == 1 -> {
                    LoadingIndicator(Modifier.fillMaxSize())
                }
                !state.isLoading && state.drinksList.isEmpty() && state.keyword.isNotEmpty() -> {
                    SearchMessageView(message = "Nothing found for " + state.keyword)
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding() + 10.dp)
                    ) {
                        items(state.drinksList.size) { index ->
                            // When reaching last Item Trigger next Page Event
                            val itemsCount = state.drinksList.size
                            if (index >= (itemsCount - 1)) {
                                LaunchedEffect(key1 = true) {
                                    viewModel.onTriggerEvent(DrinkSearchEvent.NextPageEvent)
                                }
                            }
                            DrinkSearchItem(
                                navController = navController,
                                drink = state.drinksList[index]
                            )
                        }

                        if (state.isLoading && state.page > 1) {
                            item {
                                LoadingIndicator(Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SearchMessageView(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = Icons.Default.Search,
            tint = Color.Black,
            contentDescription = "Search Icon"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            message,
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
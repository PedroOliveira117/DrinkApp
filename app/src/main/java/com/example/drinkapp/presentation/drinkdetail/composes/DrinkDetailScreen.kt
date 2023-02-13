package com.example.drinkapp.presentation.drinkdetail.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.drinkapp.common.calcDominantColor
import com.example.drinkapp.common.composes.LoadingIndicator
import com.example.drinkapp.common.extensions.noRippleClickable
import com.example.drinkapp.presentation.drinkdetail.viewmodel.DrinkDetailEvent
import com.example.drinkapp.presentation.drinkdetail.viewmodel.DrinkDetailViewModel
import com.example.drinkapp.ui.theme.*

/**
 * Created by pedrooliveira on 17/01/2023
 * All rights reserved GoodBarber
 */
@Composable
fun DrinkDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: DrinkDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state

    var dominantColor by remember {
        mutableStateOf(Color.White)
    }

    val favoriteColor = if (state.isFavorite) {
        Color.White
    } else {
        dominantColor
    }

    if (state.isLoading) {
        LoadingIndicator(Modifier.fillMaxSize())
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = gray_200)
        ) {
            state.drink?.let { drink ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(bottomStart = 40.dp))
                        .background(color = dominantColor)
                        .padding(20.dp)
                        .statusBarsPadding()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = Color.White,
                            contentDescription = "Back Icon",
                            modifier = Modifier
                                .size(42.dp)
                                .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                                .padding(10.dp)
                                .noRippleClickable {
                                    navController.popBackStack()
                                },
                        )

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            tint = favoriteColor,
                            contentDescription = "Favorite Icon",
                            modifier = Modifier
                                .size(42.dp)
                                .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                                .padding(10.dp)
                                .noRippleClickable {
                                    viewModel.onTriggerEvent(DrinkDetailEvent.UpdateFavoriteEvent)
                                },
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(drink.thumbUrl)
                                .crossfade(true)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .build(),
                            onSuccess = { state ->
                                state.result.drawable.calcDominantColor { color ->
                                    dominantColor = color
                                }
                            },
                            imageLoader = LocalContext.current.imageLoader,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(LocalConfiguration.current.screenHeightDp.dp * 0.4f)
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = drink.name,
                                fontSize = 30.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                drink.tag,
                                fontSize = 20.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Italic,
                                color = Color.White
                            )
                        }

                    }
                }

                Box(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {

                        DetailInfoRow(drink = drink)

                        DetailDescription(drink = drink)

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
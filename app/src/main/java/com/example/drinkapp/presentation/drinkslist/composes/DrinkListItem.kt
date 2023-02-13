package com.example.drinkapp.presentation.drinkslist.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.drinkapp.common.calcDominantColor
import com.example.drinkapp.common.extensions.noRippleClickable
import com.example.drinkapp.common.navigation.NavigationUtils.navigateToDrinkDetail
import com.example.drinkapp.domain.models.Drink
import com.example.drinkapp.ui.theme.*

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
@Composable
fun DrinkListItem(
    navController: NavController,
    drink: Drink,
    isFavorite: Boolean,
    onFavClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var dominantColor by remember {
        mutableStateOf(gray_300)
    }

    Column(
        modifier = modifier.padding(top = 40.dp, bottom = 20.dp, start = 8.dp, end = 8.dp)
    ) {
        Box {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .noRippleClickable {
                        navController.navigateToDrinkDetail(drinkId = drink.id)
                    }
                    .shadow(5.dp, RoundedCornerShape(20.dp))
                    .background(color = gray_300)
                    .drawWithCache {
                        onDrawBehind {
                            drawCircle(
                                dominantColor,
                                center = Offset(x = 0f, y = 50f)
                            )
                        }
                    }
            ) {
                Spacer(modifier = Modifier.height(210.dp))

                Text(
                    text = drink.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = dominantColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.BottomCenter)
                )
            }

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
                onError = {
                     dominantColor = Color.Black
                },
                imageLoader = LocalContext.current.imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(200.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = ((-40).dp))
            )

            val color = if (isFavorite) {
                dominantColor
            } else {
                gray_300
            }
            Icon(
                imageVector = Icons.Default.Favorite,
                tint = color,
                contentDescription = "Favorite Icon",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(30.dp)
                    .offset((-5).dp, 10.dp)
                    .background(color = dominantColor.copy(0.5f), shape = CircleShape)
                    .padding(5.dp)
                    .noRippleClickable {
                        onFavClick()
                    }
            )
        }
    }
}
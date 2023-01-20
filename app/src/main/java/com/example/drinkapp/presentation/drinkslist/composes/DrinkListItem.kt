package com.example.drinkapp.presentation.drinkslist.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import com.example.drinkapp.common.navigation.NavigationUtils.navigateToDrinkDetail
import com.example.drinkapp.domain.models.Drink
import com.example.drinkapp.ui.theme.gray_300
import com.example.drinkapp.ui.theme.purple_300
import com.example.drinkapp.ui.theme.purple_700

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
@Composable
fun DrinkListItem(
    navController: NavController,
    drink: Drink,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable {
                navController.navigateToDrinkDetail(drinkId = drink.id)
            }
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .height(300.dp)
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    listOf(purple_300, purple_700)
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(drink.thumbUrl)
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    imageLoader = LocalContext.current.imageLoader,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(200.dp)
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = drink.name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = gray_300,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(28.dp)
                    .offset(x = (-8).dp, y = (8.dp)),
                onClick = { /*TODO Add To Favorites*/ }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    tint = gray_300,
                    contentDescription = "Favorite Icon",
                )
            }
        }
    }
}
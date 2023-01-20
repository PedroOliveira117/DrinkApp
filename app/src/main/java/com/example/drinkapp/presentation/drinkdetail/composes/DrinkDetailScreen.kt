package com.example.drinkapp.presentation.drinkdetail.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.drinkapp.presentation.drinkdetail.viewmodel.DrinkDetailViewModel
import com.example.drinkapp.ui.theme.gray_300
import com.example.drinkapp.ui.theme.purple_500
import com.example.drinkapp.ui.theme.purple_700

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
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.linearGradient(
                    listOf(purple_500, purple_700)
                )
            )
    ) {
        state.drink?.let { drink ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
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
                        .height(LocalConfiguration.current.screenHeightDp.dp * 0.4f)
                        .align(Alignment.Center)
                )

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .size(38.dp)
                        .align(Alignment.TopStart)
                        .offset(x = (-5).dp, y = (-5).dp)
                        .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                        .padding(8.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                )

                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    tint = Color.White,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .size(38.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = 5.dp, y = (-5).dp)
                        .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                        .padding(8.dp)
                        .clickable { },
                )
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = drink.name,
                        fontSize = 25.sp,
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        drink.tag,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        color = gray_300,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    DetailInfoRow(drink = drink)

                    DetailDescription(drink = drink)

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
package com.example.drinkapp.presentation.beerslist.composes

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */
@Composable
fun BeersListItem(
    beer: Drink,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Color.Gray,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .height(300.dp)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(beer.thumbUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(200.dp)
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = beer.name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        color = Color.White,
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
                    tint = Color.White,
                    contentDescription = "Favorite Icon",
                )
            }
        }
    }
}
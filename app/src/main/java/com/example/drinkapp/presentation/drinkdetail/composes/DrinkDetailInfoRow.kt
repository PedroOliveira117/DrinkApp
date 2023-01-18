package com.example.drinkapp.presentation.drinkdetail.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drinkapp.R
import com.example.drinkapp.domain.models.Drink

/**
 * Created by pedrooliveira on 18/01/2023
 * All rights reserved GoodBarber
 */

@Composable
fun DetailInfoRow(
    modifier: Modifier = Modifier,
    drink: Drink
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        DetailInfoItem(
            modifier = Modifier.weight(1f),
            iconResourceId = R.drawable.ic_detail_date,
            text = drink.brewedDate
        )
        DetailInfoItem(
            modifier = Modifier.weight(1f),
            iconResourceId = R.drawable.ic_detail_volume,
            text = drink.volume.value.toString() + "L"
        )
        DetailInfoItem(
            modifier = Modifier.weight(1f),
            iconResourceId = R.drawable.ic_detail_ph,
            text = drink.ph.toString()
        )
    }
}

@Composable
fun DetailInfoItem(
    modifier: Modifier = Modifier,
    iconResourceId: Int,
    text: String
) {
    Box(modifier = modifier
        .padding(10.dp)
        .shadow(5.dp, shape = RoundedCornerShape(15.dp))
        .background(color = Color.LightGray)) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconResourceId),
                tint = Color.White,
                contentDescription = "Info Icon",
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight(700),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
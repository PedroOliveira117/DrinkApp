package com.example.drinkapp.presentation.drinkdetail.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drinkapp.domain.models.Drink
import com.example.drinkapp.ui.theme.gray_300
import com.example.drinkapp.ui.theme.purple_700
import com.example.drinkapp.ui.theme.yellow_500

/**
 * Created by pedrooliveira on 18/01/2023
 * All rights reserved GoodBarber
 */
@Composable
fun DetailDescription(
    modifier: Modifier = Modifier,
    drink: Drink
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, shape = RoundedCornerShape(10.dp))
                .background(color = purple_700)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    "Description",
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(700),
                    color = yellow_500
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    drink.description,
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(500),
                    color = gray_300,
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, shape = RoundedCornerShape(10.dp))
                .background(color = purple_700)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    "Matches well with",
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(700),
                    color = yellow_500
                )

                Spacer(modifier = Modifier.height(15.dp))

                drink.foodMatches.forEach { foodMatch ->
                    Text(
                        foodMatch,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(700),
                        color = gray_300
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}
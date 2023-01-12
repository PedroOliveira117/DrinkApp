package com.example.drinkapp.common.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Center),
            color = color
        )
    }
}
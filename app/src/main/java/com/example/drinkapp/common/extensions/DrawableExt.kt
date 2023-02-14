package com.example.drinkapp.common

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette

/**
 * Created by pedrooliveira on 07/02/2023
 * All rights reserved GoodBarber
 */
fun Drawable.calcDominantColor(onFinish: (Color) -> Unit) {
    val bmp = (this as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    Palette.from(bmp).generate { palette ->
        palette?.getVibrantColor(Color.Black.toArgb())?.let { colorValue ->
            onFinish(Color(colorValue))
        }
    }
}
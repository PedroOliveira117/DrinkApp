package com.example.drinkapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */

@HiltAndroidApp
class DrinkApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .respectCacheHeaders(false)
            .build()
    }
}
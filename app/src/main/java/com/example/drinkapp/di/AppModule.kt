package com.example.drinkapp.di

import android.app.Application
import androidx.room.Room
import com.example.drinkapp.common.ApiConstants
import com.example.drinkapp.data.local.DrinksDataBase
import com.example.drinkapp.data.remote.DrinksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDrinksApi(): DrinksApi {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DrinksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDrinksDataBase(drinkApplication: Application) = Room
        .databaseBuilder(drinkApplication, DrinksDataBase::class.java, "drink_database").
        fallbackToDestructiveMigration()
        .build()
}
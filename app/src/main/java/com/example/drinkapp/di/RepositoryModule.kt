package com.example.drinkapp.di

import com.example.drinkapp.data.repository.DrinksRepository
import com.example.drinkapp.domain.repository.IDrinksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by pedrooliveira on 12/01/2023
 * All rights reserved GoodBarber
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDrinksRepository(repository: DrinksRepository): IDrinksRepository
}
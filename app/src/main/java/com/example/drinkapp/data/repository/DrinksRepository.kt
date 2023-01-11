package com.example.drinkapp.data.repository

import com.example.drinkapp.common.Resource
import com.example.drinkapp.data.remote.DrinksApi
import com.example.drinkapp.domain.models.Drink
import com.example.drinkapp.domain.repository.IDrinksRepository
import javax.inject.Inject

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */
class DrinksRepository @Inject constructor(private val api: DrinksApi) : IDrinksRepository {

    override suspend fun getBeers(): Resource<ArrayList<Drink>> {
        val response = try {
            api.getBeers()
        } catch (e: Exception) {
            return Resource.Error(message = "Error while getting drinks")
        }
        return Resource.Success(data = response)
    }
}
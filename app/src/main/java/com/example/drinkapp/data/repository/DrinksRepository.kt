package com.example.drinkapp.data.repository

import com.example.drinkapp.common.Resource
import com.example.drinkapp.data.local.DrinksDataBase
import com.example.drinkapp.data.remote.DrinksApi
import com.example.drinkapp.domain.models.Drink
import com.example.drinkapp.domain.repository.IDrinksRepository
import javax.inject.Inject

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */
class DrinksRepository @Inject constructor(private val api: DrinksApi, private val dataBase: DrinksDataBase) : IDrinksRepository {

    override suspend fun getBeers(page: Int, perPage: Int): Resource<List<Drink>> {
        val response = try {
            api.getBeers(page, perPage)
        } catch (e: Exception) {
            // If fails get Drinks from cache
            return Resource.Error(message = "Error while getting drinks", data = dataBase.drinkDao().getBeers(page - 1, perPage))
        }
        // Save Drinks In cache
        dataBase.drinkDao().insertDrink(response)
        return Resource.Success(data = response)
    }
}
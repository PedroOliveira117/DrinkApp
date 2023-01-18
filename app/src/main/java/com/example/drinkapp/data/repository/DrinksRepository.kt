package com.example.drinkapp.data.repository

import com.example.drinkapp.common.Resource
import com.example.drinkapp.data.local.DrinksDataBase
import com.example.drinkapp.data.models.toDrink
import com.example.drinkapp.data.remote.DrinksApi
import com.example.drinkapp.domain.models.Drink
import com.example.drinkapp.domain.repository.IDrinksRepository
import javax.inject.Inject

/**
 * Created by pedrooliveira on 10/01/2023
 * All rights reserved GoodBarber
 */
class DrinksRepository @Inject constructor(private val api: DrinksApi, private val dataBase: DrinksDataBase) : IDrinksRepository {

    override suspend fun getDrinks(page: Int, perPage: Int): Resource<List<Drink>> {
        val response = try {
            api.getDrinks(page, perPage)
        } catch (e: Exception) {
            // If fails get Drinks from cache
            return Resource.Error(message = "Error while getting drinks", data = dataBase.drinkDao().getDrinks(page - 1, perPage).map { it.toDrink() })
        }
        // Save Drinks In cache
        dataBase.drinkDao().insertDrink(response)

        return Resource.Success(data = response.map { it.toDrink() })
    }

    override suspend fun getDrinkById(id: String): Resource<Drink> {
        val response = try {
            api.getDrinkById(drinkId = id)
        } catch (e: Exception) {
            // If fails get Drink from cache
            return Resource.Error(message = "Error while getting drink with id: $e", data = dataBase.drinkDao().getDrinkById(drinkId = id).toDrink())
        }
        return Resource.Success(data = response.first().toDrink())
    }

    override suspend fun searchDrink(keyword: String, page: Int, perPage: Int): Resource<List<Drink>> {
        val response = try {
            api.searchDrink(keyword = keyword, page = page, perPage = perPage)
        } catch (e: Exception) {
            // If fails search Drink in cache
            return Resource.Error(message = "Error while searching for drink $keyword", data = dataBase.drinkDao().searchDrink(
                keyword = keyword,
                page = page - 1,
                perPage = perPage
            ).map {
                it.toDrink()
            })
        }
        return Resource.Success(data = response.map { it.toDrink() })
    }
}
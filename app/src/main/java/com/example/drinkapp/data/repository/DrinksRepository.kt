package com.example.drinkapp.data.repository

import com.example.drinkapp.common.Resource
import com.example.drinkapp.data.local.DrinksDataBase
import com.example.drinkapp.data.models.DrinkDto
import com.example.drinkapp.data.models.DrinkFavoriteDto
import com.example.drinkapp.data.models.MeasureDto
import com.example.drinkapp.data.models.toDrink
import com.example.drinkapp.data.remote.DrinksApi
import com.example.drinkapp.domain.models.Drink
import com.example.drinkapp.domain.repository.IDrinksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
            return Resource.Error(
                message = "Error while getting drinks",
                data = dataBase
                    .drinkDao()
                    .getDrinks(page - 1, perPage)
                    .map {
                        it.toDrink()
                    }
            )
        }
        // Save Drinks In cache
        dataBase.drinkDao().insertDrink(response)

        return Resource.Success(
            data = response.map {
                it.toDrink()
            }
        )
    }

    override suspend fun getDrinkById(id: String): Resource<Drink> {
        val response = try {
            api.getDrinkById(drinkId = id)
        } catch (e: Exception) {
            // If fails get Drink from cache
            return Resource.Error(
                message = "Error while getting drink with id: $e",
                data = dataBase
                    .drinkDao()
                    .getDrinkById(drinkId = id)
                    .first()
                    .toDrink()
            )
        }

        // Means the item doesn't exist anymore
        // Need to remove it from cache
        if (response.isEmpty()) {
            deleteDrinksByIds(listOf(id))
            return Resource.Error(
                message = "Drink no longer exists"
            )
        }
        return Resource.Success(
            data = response.first()
                .toDrink()
        )
    }

    override suspend fun getDrinkByIds(ids: List<String>): Resource<List<Drink>> {
        // Format ids string as 1|2|3 (format handle by API)
        var formattedIds = ""
        ids.forEachIndexed { index, id ->
            formattedIds += if (ids.size != index - 1) {
                "$id|"
            } else {
                id
            }
        }

        val response = try {
            api.getDrinkById(drinkId = formattedIds)
        } catch (e: Exception) {
            // If fails get Drink from cache
            return Resource.Error(
                message = "Error while getting drink with id: $e",
                data = dataBase
                    .drinkDao()
                    .getDrinkByIds(drinkIds = ids)
                    .sortedBy {
                        // Order the list by the ids requests
                        ids.indexOf(it.id)
                    }
                    .map {
                        it.toDrink()
                    }
            )
        }

        // Means some items were deleted from API
        // Need to remove them from cache
        if (response.size != ids.size) {
            // Find the ids that don't exist on the response
            ids.filter { id ->
                !response.any { drink ->
                    drink.id == id
                }
            }.let { idsToDelete ->
                deleteDrinksByIds(idsToDelete)
            }
        }

        return Resource.Success(
            data = response
                .sortedBy {
                    // Order the list by the ids requests
                    ids.indexOf(it.id)
                }.map {
                    it.toDrink()
                }
        )
    }

    private suspend fun deleteDrinksByIds(idsList: List<String>) {
        dataBase.drinkDao().removeDrinks(
            drinkList = idsList.map { id ->
                DrinkDto(
                    id = id,
                    name = "",
                    thumbUrl = "",
                    tag = "",
                    description = "",
                    brewedDate = "",
                    brewersTips = "",
                    ph = 0f,
                    volume = MeasureDto(value = 0, unit = ""),
                    foodMatches = emptyList()
                )
            }
        )
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
        return Resource.Success(
            data = response.map {
                it.toDrink()
            }
        )
    }

    override suspend fun getFavoriteDrinks(): Flow<List<Drink>> = dataBase.drinkDao().getFavoriteDrinks().map { list ->
        list.map {
            it.toDrink()
        }
    }

    override suspend fun insertFavorite(id: String) = dataBase.drinkFavoriteDao().insertFavorite(DrinkFavoriteDto(id, System.currentTimeMillis()))

    override suspend fun removeFavorite(id: String) = dataBase.drinkFavoriteDao().removeFavorite(DrinkFavoriteDto(id))
}
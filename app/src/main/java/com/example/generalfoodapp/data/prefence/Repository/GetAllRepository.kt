package com.example.generalfoodapp.data.prefence.Repository

import com.example.generalfoodapp.data.prefence.model.FoodModel


interface GetAllFoodRepository {
    suspend fun getAllFoodsAsync(): List<FoodModel>
}
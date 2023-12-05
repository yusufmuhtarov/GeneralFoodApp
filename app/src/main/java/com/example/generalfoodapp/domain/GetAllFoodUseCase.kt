package com.example.generalfoodapp.domain

import com.example.generalfoodapp.data.prefence.model.FoodModel


interface GetAllFoodUseCase {
    suspend fun getAllFoods(): List<FoodModel>
}
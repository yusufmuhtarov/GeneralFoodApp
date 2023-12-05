package com.example.generalfoodapp.domain

import com.example.generalfoodapp.data.prefence.Repository.GetAllFoodsRepositoriesImpl
import com.example.generalfoodapp.data.prefence.model.FoodModel


class GetAllFoodUseCaseImpl: GetAllFoodUseCase {
    private val foodsRepository = GetAllFoodsRepositoriesImpl()
    override suspend fun getAllFoods(): List<FoodModel>{
        return foodsRepository.getAllFoodsAsync()
    }
}
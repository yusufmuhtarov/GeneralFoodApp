package com.example.generalfoodapp.data.prefence.Repository

import com.example.generalfoodapp.data.prefence.fake_data.foodFakeDatas
import com.example.generalfoodapp.data.prefence.model.FoodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllFoodsRepositoriesImpl:GetAllFoodRepository{
    override suspend fun getAllFoodsAsync(): List<FoodModel> = foodFakeDatas()
}
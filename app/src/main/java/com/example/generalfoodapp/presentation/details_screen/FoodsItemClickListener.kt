package com.example.generalfoodapp.presentation.details_screen

import com.example.generalfoodapp.data.prefence.model.FoodModel

interface FoodsItemClickListener {
    fun OnClickToDesert(model: FoodModel)
    fun OnClickToDelete(index:Int)
}
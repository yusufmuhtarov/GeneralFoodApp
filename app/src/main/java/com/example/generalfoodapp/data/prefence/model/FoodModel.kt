package com.example.generalfoodapp.data.prefence.model

import java.io.Serializable

data class FoodModel(
    val name: String,
    val description: String,
    val price: Int,
    val img: String,
) : Serializable
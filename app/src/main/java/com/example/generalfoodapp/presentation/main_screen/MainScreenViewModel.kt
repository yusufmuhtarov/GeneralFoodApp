package com.example.generalfoodapp.presentation.main_screen

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.generalfoodapp.data.prefence.Repository.GetAllFoodsRepositoriesImpl
import com.example.generalfoodapp.data.prefence.model.FoodModel
import com.example.generalfoodapp.domain.GetAllFoodUseCaseImpl
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val getAllFoodUseCase = GetAllFoodUseCaseImpl()

    val foodLivaData: MutableLiveData<List<FoodModel>> = MutableLiveData()

    init {
        getAllFoods()
    }

    private fun getAllFoods() {
        viewModelScope.launch {
            val response = getAllFoodUseCase.getAllFoods()
            foodLivaData.postValue(response)
        }
    }
}
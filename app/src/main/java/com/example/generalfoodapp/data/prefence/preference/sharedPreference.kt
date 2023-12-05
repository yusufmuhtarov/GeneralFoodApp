package com.example.generalfoodapp.data.prefence.preference

import android.content.Context
import com.example.generalfoodapp.data.prefence.model.FoodModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val FOOD_SHARED_PREFERENCES_KEY = "FOOD_SHARED_PREFERENCES_KEY"
private const val FOOD_PREF_KEY = "FOOD_PREF_KEY"

class sharedPreference(
    private val context: Context
) {
    private val sharedPreference = context.getSharedPreferences(
        FOOD_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
    )
    private val gson = Gson()
    fun saveFood(foodModel: FoodModel) {
        val notes = getAllSavedFood().toMutableList()
        notes.add(0, foodModel)
        val notesGson = Gson().toJson(notes)
        sharedPreference
            .edit()
            .putString(FOOD_PREF_KEY, notesGson)
            .apply()
    }

    fun getAllSavedFood(): List<FoodModel> {
        val json = sharedPreference.getString(FOOD_PREF_KEY, null)
        val type: Type = object : TypeToken<List<FoodModel?>?>() {}.type
        val foodList = gson.fromJson<List<FoodModel>>(json, type)
        return foodList ?: emptyList()
    }

    fun deleteAllSavedFoods() {
        sharedPreference.edit().clear().apply()
    }

    fun deleteByIndex(index: Int) {
        val getAllNotes = getAllSavedFood().toMutableList()
        if (index in 0 until getAllSavedFood().size) {
            getAllNotes.removeAt(index)
            val noteGsonString = Gson().toJson(getAllNotes)
            sharedPreference.edit().putString(FOOD_PREF_KEY, noteGsonString).apply()
        }
    }


}
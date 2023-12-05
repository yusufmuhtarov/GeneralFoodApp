package com.example.generalfoodapp.presentation.details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.generalfoodapp.R
import com.example.generalfoodapp.data.prefence.model.FoodModel
import com.example.generalfoodapp.data.prefence.preference.sharedPreference
import com.example.generalfoodapp.databinding.FragmentFoodDetailScreenBinding
import com.example.generalfoodapp.presentation.main_screen.MainScreenFragment.Companion.FOOD_KEY
import com.google.android.material.snackbar.Snackbar


class FoodDetailScreen : Fragment() {

    private val binding: FragmentFoodDetailScreenBinding by lazy {
        FragmentFoodDetailScreenBinding.inflate(layoutInflater)
    }

    private val sharedPreference: sharedPreference by lazy {
        sharedPreference(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBackDispatcher()
        setUpViewStatusBar()
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        val foodModel = arguments?.getSerializable(FOOD_KEY) as? FoodModel
        foodModel?.let {
            setUpViews(it)
            setUpClickListeners(foodModel)
        }
    }

    private fun setUpBackDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    private fun setUpViewStatusBar() {
        requireActivity().window?.statusBarColor = resources.getColor(R.color.white)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }


    fun setUpViews(foodModel: FoodModel) {
        binding.apply {
            Glide.with(requireContext())
                .load(foodModel.img)
                .into(foodImg)
            foodNameTv.text = foodModel.name
            foodDescription.text = foodModel.description
            foodPriceTv.text = foodModel.price.toString()
        }
    }

    private fun setUpClickListeners(foodModel: FoodModel) {
        binding.button.setOnClickListener {
            sharedPreference.saveFood(foodModel)
            Snackbar.make(
                requireView(), "ADDED To Cart",
                Snackbar.LENGTH_SHORT
            ).show()
                findNavController().popBackStack()
        }
    }
}
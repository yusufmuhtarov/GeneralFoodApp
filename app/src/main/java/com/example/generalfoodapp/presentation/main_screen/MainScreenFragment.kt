package com.example.generalfoodapp.presentation.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.generalfoodapp.R
import com.example.generalfoodapp.databinding.FragmentMainScreenBinding
import com.example.generalfoodapp.presentation.adapter.FoodAdapter

class MainScreenFragment : Fragment() {

    private val binding: FragmentMainScreenBinding by lazy {
        FragmentMainScreenBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainScreenViewModel

    private val adapter: FoodAdapter by lazy {
        FoodAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[MainScreenViewModel::class.java]

        statusBarColor()

        viewModel.foodLivaData.observe(viewLifecycleOwner) { foodList ->
            adapter.updateList(foodList)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun statusBarColor() {
        requireActivity().window?.statusBarColor = resources.getColor(R.color.unBoardingColor)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.unBoardingColor)
    }
}
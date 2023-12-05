package com.example.generalfoodapp.presentation.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.generalfoodapp.R
import com.example.generalfoodapp.data.prefence.model.FoodModel
import com.example.generalfoodapp.databinding.FragmentMainScreenBinding
import com.example.generalfoodapp.presentation.adapter.FoodAdapter
import com.example.generalfoodapp.presentation.details_screen.FoodsItemClickListener

class MainScreenFragment : Fragment(), FoodsItemClickListener {

    private val binding: FragmentMainScreenBinding by lazy {
        FragmentMainScreenBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: MainScreenViewModel

    private var foodList: List<FoodModel> = emptyList()

    private val foodAdapter: FoodAdapter by lazy {
        FoodAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnSearch()
        binding.basketCard.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainScreenFragment_to_cartFragment
            )
        }

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[MainScreenViewModel::class.java]

        setUpViews()
        setUpViewStatusBar()
        setUpObserveData()
    }

    private fun setUpViewStatusBar() {
        requireActivity().window?.statusBarColor = resources.getColor(R.color.btnColor)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }

    private fun setUpObserveData() = viewModel.apply {
        foodLivaData.observe(viewLifecycleOwner) { food ->
            foodAdapter.updateList(food)
            foodList = food
        }
    }

    private fun setUpViews() = binding.apply {
        recyclerView.adapter = foodAdapter
    }

    private fun fitherFood(title: String) {
        val fither = foodList.filter { name ->
            name.name.contains(title, ignoreCase = true)
        }
        foodAdapter.updateList(fither)
    }

    private fun setOnSearch() = binding.apply {
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    fitherFood(it)
                }
                return true
            }
        })
    }

    companion object {
        const val FOOD_KEY = "FOOD_KEY"
    }

    override fun OnClickToDesert(model: FoodModel) {
        findNavController().navigate(
            R.id.action_mainScreenFragment_to_foodDetailScreen, bundleOf(FOOD_KEY to model)
        )
    }

    override fun OnClickToDelete(index: Int) {
        TODO("Not yet implemented")
    }
}
package com.example.generalfoodapp.presentation.cart_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.generalfoodapp.R
import com.example.generalfoodapp.data.prefence.model.FoodModel
import com.example.generalfoodapp.data.prefence.preference.sharedPreference
import com.example.generalfoodapp.databinding.FragmentCardBinding
import com.example.generalfoodapp.presentation.adapter.FoodAdapter
import com.example.generalfoodapp.presentation.details_screen.FoodsItemClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CartFragment : Fragment(), FoodsItemClickListener {

    private val binding: FragmentCardBinding by lazy {
        FragmentCardBinding.inflate(layoutInflater)
    }

    private val adapter: FoodAdapter by lazy {
        FoodAdapter(this)
    }

    private val sharedPreference: sharedPreference by lazy {
        sharedPreference(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        OnClickToBackButton()
        binding.backIv.setOnClickListener {
            findNavController().navigate(
                R.id.action_cartFragment_to_mainScreenFragment
            )
        }
        statusBarColor()
        setUpViews()
        binding.deleteAllFoodBtn.setOnClickListener{
            showConfirmDeleteDialog()
        }
    }

    private fun statusBarColor() {
        requireActivity().window?.statusBarColor = resources.getColor(R.color.btnColor)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.btnColor)
    }

    private fun OnClickToBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    private fun setUpViews() {
        val saveFoodList = sharedPreference.getAllSavedFood()
        adapter.updateList(saveFoodList)
        binding.recyclerView.adapter = adapter
    }

    override fun OnClickToDesert(model: FoodModel) {
        TODO("Not yet implemented")
    }

    override fun OnClickToDelete(index: Int) {
        sharedPreference.deleteByIndex(index)
        setUpViews()
    }

    private fun deleteAllSavedNotes() {
        sharedPreference.deleteAllSavedFoods()
        adapter.updateList(emptyList())
    }

    private fun showConfirmDeleteDialog() {
        val alertDialog = MaterialAlertDialogBuilder(requireContext())
        alertDialog.setMessage("Do you really want to delete all notes")
        alertDialog.setPositiveButton("yes") { dialog, _ ->
            deleteAllSavedNotes()
            dialog.dismiss()
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.create().show()
    }

}
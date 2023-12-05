package com.example.generalfoodapp.presentation.unBoardingscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.donutsapp.preferences.UserEnteredSheredPref
import com.example.generalfoodapp.R
import com.example.generalfoodapp.databinding.UnBoardingScreenBinding

class OnBoardingScreenFragment : Fragment() {

    private val binding: UnBoardingScreenBinding by lazy {
        UnBoardingScreenBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: UserEnteredSheredPref by lazy {
        UserEnteredSheredPref(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (sharedPreferences.getIsUserFirstSign()) {
            findNavController().navigate(
                R.id.action_unBoardingScreenFragment_to_mainScreenFragment
            )
        } else {
            binding.getStartedBtn.setOnClickListener {
                sharedPreferences.setUserFirstSign(true)
                findNavController().navigate(
                    R.id.action_unBoardingScreenFragment_to_mainScreenFragment
                )
            }
        }
    }
}
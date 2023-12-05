package com.example.generalfoodapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.generalfoodapp.R
import com.example.generalfoodapp.data.prefence.model.FoodModel
import com.example.generalfoodapp.databinding.ItemBinding
import com.example.generalfoodapp.presentation.details_screen.FoodsItemClickListener


class FoodAdapter( private val listener: FoodsItemClickListener
) : RecyclerView.Adapter<FoodAdapter.FoodAppViewHolder>() {

    fun updateList(desertList: List<FoodModel>) {
        foodList.clear()
        foodList.addAll(desertList)
        notifyDataSetChanged()
    }

    private val foodList = mutableListOf<FoodModel>()

    inner class FoodAppViewHolder(
        private val binding: ItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: FoodModel) {
            binding.foodNameTv.text = model.name
            binding.foodDescription.text = model.description
            binding.foodPriceTv.text = model.price.toString()
            Glide.with(binding.root).load(model.img).into(binding.foodImg)
            binding.itemCard.setOnClickListener {
                listener.OnClickToDesert(model)
            }
            binding.deleteFoodBtm.setOnClickListener{
                listener.OnClickToDelete(foodList.indexOf(model))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAppViewHolder {
        val binding = ItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
        return FoodAppViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FoodAppViewHolder, position: Int) {
        holder.bind(foodList[position])
    }
    override fun getItemCount(): Int = foodList.size
}
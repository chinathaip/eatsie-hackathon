package com.stamford.hackathon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stamford.hackathon.databinding.ViewHolderRestaurantBinding
import com.stamford.hackathon.ui.main.viewholder.RestaurantViewHolder

class RestaurantAdapter : RecyclerView.Adapter<RestaurantViewHolder>() {

    val restaurants = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            ViewHolderRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = restaurants.size
}
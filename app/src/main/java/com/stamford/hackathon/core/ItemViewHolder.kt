package com.stamford.hackathon.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun fillData(data: T, position: Int)
}
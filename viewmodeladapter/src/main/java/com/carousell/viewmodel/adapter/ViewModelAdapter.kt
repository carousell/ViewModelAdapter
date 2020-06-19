package com.carousell.viewmodel.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class ViewModelAdapter(callback: DiffUtil.ItemCallback<ItemViewModel>) :
    ListAdapter<ItemViewModel, ViewModelHolder<out ItemViewModel>>(callback) {
    
    override fun onBindViewHolder(holder: ViewModelHolder<out ItemViewModel>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: ViewModelHolder<out ItemViewModel>) {
        holder.unbind()
    }
}
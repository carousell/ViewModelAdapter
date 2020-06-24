package com.carousell.viewmodel.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * ViewModelAdapter to handle ViewModel List as data source
 */
abstract class ViewModelAdapter(callback: DiffUtil.ItemCallback<ItemViewModel> = ItemViewModel.ItemDiffCallback()) :
    ListAdapter<ItemViewModel, ViewModelHolder<out ItemViewModel>>(callback) {

    override fun onBindViewHolder(holder: ViewModelHolder<out ItemViewModel>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: ViewModelHolder<out ItemViewModel>) {
        holder.unbind()
    }
}
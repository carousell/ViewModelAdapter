package com.carousell.viewmodel.adapter

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil

abstract class ItemViewModel : ViewModel() {
    abstract fun getKey(): String

    abstract fun compare(viewModel: ItemViewModel): Boolean

    class ItemDiffCallback : DiffUtil.ItemCallback<ItemViewModel>() {
        override fun areItemsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel): Boolean {
            return oldItem.getKey() == newItem.getKey()
        }

        override fun areContentsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel): Boolean {
            return oldItem.compare(newItem)
        }

    }
}
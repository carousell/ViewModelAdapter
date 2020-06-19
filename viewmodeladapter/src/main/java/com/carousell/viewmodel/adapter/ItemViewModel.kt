package com.carousell.viewmodel.adapter

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil

/**
 * ViewModel which will used to interact with ViewModelHolder
 */
abstract class ItemViewModel : ViewModel() {
    /**
     * Unique key to identify ViewModel, will be used in ItemDiffCallback
     */
    abstract fun getKey(): String

    /**
     * Check whether two ViewModel has same content for not, will be used in ItemDiffCallback
     */
    abstract fun compare(viewModel: ItemViewModel): Boolean

    /**
     * Default implementation of DiffUtil.ItemCallback for ItemViewModel, can be override if you have custom logic
     */
    class ItemDiffCallback : DiffUtil.ItemCallback<ItemViewModel>() {
        override fun areItemsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel): Boolean {
            return oldItem.getKey() == newItem.getKey()
        }

        override fun areContentsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel): Boolean {
            return oldItem.compare(newItem)
        }

    }
}
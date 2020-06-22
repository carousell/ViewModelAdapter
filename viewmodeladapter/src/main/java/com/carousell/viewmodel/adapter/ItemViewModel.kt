package com.carousell.viewmodel.adapter

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil

/**
 * ViewModel which will be used to interact with ViewModelHolder
 */
abstract class ItemViewModel : ViewModel() {
    /**
     * Unique key to identify ViewModel. Will be used in ItemDiffCallback
     */
    abstract fun getKey(): String

    /**
     * Check whether two ViewModels have the same content. Will be used in ItemDiffCallback
     */
    abstract fun compare(viewModel: ItemViewModel): Boolean

    /**
     * Default implementation of DiffUtil.ItemCallback for ItemViewModel, can be overridden to implement custom logic
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
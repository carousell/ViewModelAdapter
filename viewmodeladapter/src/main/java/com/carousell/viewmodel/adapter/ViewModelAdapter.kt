package com.carousell.viewmodel.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * ViewModelAdapter to handle ViewModel List as data source
 */
abstract class ViewModelAdapter(callback: DiffUtil.ItemCallback<ItemViewModel> = ItemViewModel.ItemDiffCallback()) :
    ListAdapter<ItemViewModel, ViewModelHolder<out ItemViewModel>>(callback) {

    private val modelTypeMapping = mutableMapOf<Class<out ItemViewModel>, Int>()
    private val typeModelMapping = mutableMapOf<Int, Class<out ItemViewModel>>()

    override fun onBindViewHolder(holder: ViewModelHolder<out ItemViewModel>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: ViewModelHolder<out ItemViewModel>) {
        holder.unbind()
    }

    final override fun getItemViewType(position: Int): Int {
        val clz = getItem(position).javaClass
        
        return modelTypeMapping[clz] ?: run {
            val index = modelTypeMapping.size
            modelTypeMapping[clz] = index
            typeModelMapping[index] = clz
            return@run index
        }
    }

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewModelHolder<out ItemViewModel> {
        return typeModelMapping[viewType]?.let {
            onCreateViewHolder(parent, it)
        } ?: throw RuntimeException("No support onCreateViewHolder")
    }

    abstract fun onCreateViewHolder(
        parent: ViewGroup,
        modelType: Class<out ItemViewModel>
    ): ViewModelHolder<out ItemViewModel>
}
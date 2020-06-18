package com.carousell.viewmodel.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelAdapter : RecyclerView.Adapter<ViewModelHolder<out ItemViewModel>>() {

    protected val viewModels = mutableListOf<ItemViewModel>()

    override fun getItemCount() = viewModels.size

    override fun onBindViewHolder(holder: ViewModelHolder<out ItemViewModel>, position: Int) {
        holder.bind(viewModels[position])
    }

    override fun onViewRecycled(holder: ViewModelHolder<out ItemViewModel>) {
        holder.unbind()
    }

    fun setData(viewModels: List<ItemViewModel>) {
        val diffResult = DiffUtil.calculateDiff(ViewModelDiffCallback(viewModels, this.viewModels))
        this.viewModels.clear()
        this.viewModels.addAll(viewModels)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewModelDiffCallback(
        private val newList: List<ItemViewModel>,
        private val oldList: List<ItemViewModel>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newList[newItemPosition].item.key == oldList[oldItemPosition].item.key
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newList[newItemPosition] == oldList[oldItemPosition]
        }

    }
}
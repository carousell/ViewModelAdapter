package com.carousell.viewmodeladapter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelAdapter<T>(
    private val viewModelStoreOwner: ViewModelStoreOwner
) : RecyclerView.Adapter<ViewModelHolder<ViewModel>>() {

    private val viewModelProviders = mutableMapOf<Int, ViewModelProvider>()

    protected val data = mutableListOf<T>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewModelHolder<ViewModel>, position: Int) {
        val viewType = getItemViewType(position)
        val viewModelClass = getViewModelClass(viewType)
        val viewModelTag = "${viewModelClass.simpleName}-$position"
        val viewModel = getViewModelProvider(viewType).get(viewModelTag, viewModelClass)
        holder.bind(viewModel)
    }

    fun setData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private fun getViewModelProvider(position: Int) =
        viewModelProviders[position] ?: onCreateViewModelProvider(
            getItemViewType(position),
            data[position]
        ).also {
            viewModelProviders[position] = it
        }

    private fun onCreateViewModelProvider(viewType: Int, item: T) =
        ViewModelProvider(viewModelStoreOwner, getViewModelProviderFactory(viewType, item))

    abstract fun getViewModelClass(viewType: Int): Class<out ViewModel>

    abstract fun getViewModelProviderFactory(viewType: Int, item: T): ViewModelProvider.Factory

}
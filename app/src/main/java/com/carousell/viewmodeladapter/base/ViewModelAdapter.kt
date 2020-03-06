package com.carousell.viewmodeladapter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelAdapter<T : Item>(
    private val viewModelStoreOwner: ViewModelStoreOwner
) : RecyclerView.Adapter<ViewModelHolder<ViewModel>>() {

    init {
        setHasStableIds(true)
    }

    private val viewModelProviders = mutableMapOf<Long, ViewModelProvider>()

    protected val data = mutableListOf<T>()

    override fun getItemCount() = data.size

    override fun getItemId(position: Int) = data[position].id

    override fun onBindViewHolder(holder: ViewModelHolder<ViewModel>, position: Int) {
        val itemId = getItemId(position)
        val viewType = getItemViewType(position)
        val viewModelClass = getViewModelClass(viewType)
        val viewModelTag = "${viewModelClass.simpleName}-$itemId"
        val viewModel = getViewModelProvider(itemId, position).get(viewModelTag, viewModelClass)
        holder.bind(viewModel)
    }

    fun setData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private fun getViewModelProvider(itemId: Long, position: Int) =
        viewModelProviders[itemId] ?: onCreateViewModelProvider(
            getItemViewType(position),
            data[position]
        ).also {
            viewModelProviders[itemId] = it
        }

    private fun onCreateViewModelProvider(viewType: Int, item: T) =
        ViewModelProvider(viewModelStoreOwner, getViewModelProviderFactory(viewType, item))

    abstract fun getViewModelClass(viewType: Int): Class<out ViewModel>

    abstract fun getViewModelProviderFactory(viewType: Int, item: T): ViewModelProvider.Factory

}
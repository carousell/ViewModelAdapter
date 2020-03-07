package com.carousell.viewmodeladapter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelAdapter<T : Item>(
    private val viewModelStoreOwner: ViewModelStoreOwner
) : RecyclerView.Adapter<ViewModelHolder<ViewModel>>() {

    private val viewModelProviders = mutableMapOf<Long, ViewModelProvider>()

    protected val data = mutableListOf<T>()

    override fun getItemCount() = data.size

    override fun getItemId(position: Int) = data[position].id

    override fun onBindViewHolder(holder: ViewModelHolder<ViewModel>, position: Int) {
        holder.bind(getViewModel(position))
    }

    override fun onViewRecycled(holder: ViewModelHolder<ViewModel>) {
        holder.unbind()
    }

    fun setData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        createViewModelProviders()
        notifyDataSetChanged()
    }

    private fun createViewModelProviders() {
        viewModelProviders.clear()
        data.forEachIndexed { position, _ ->
            getViewModel(position)
        }
    }

    private fun getViewModel(position: Int): ViewModel {
        val itemId = getItemId(position)
        val viewType = getItemViewType(position)
        val viewModelClass = getViewModelClass(viewType)
        val viewModelTag = "${viewModelClass.simpleName}-$itemId"
        return getViewModelProvider(itemId, position).get(viewModelTag, viewModelClass)
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
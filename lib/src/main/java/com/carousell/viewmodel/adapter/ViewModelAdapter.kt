package com.carousell.viewmodel.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelAdapter<T : Item>(
    private val viewModelStoreOwner: ViewModelStoreOwner
) : RecyclerView.Adapter<ViewModelHolder<out ViewModel>>() {

    private val viewModels = mutableMapOf<Long, ViewModel>()

    protected val data = mutableListOf<T>()

    override fun getItemCount() = data.size

    override fun getItemId(position: Int) = data[position].id

    override fun onBindViewHolder(holder: ViewModelHolder<out ViewModel>, position: Int) {
        viewModels[getItemId(position)]?.let { holder.bind(it) }
    }

    override fun onViewRecycled(holder: ViewModelHolder<out ViewModel>) {
        holder.unbind()
    }

    fun setData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        createViewModels()
        notifyDataSetChanged()
    }

    private fun createViewModels() {
        viewModels.clear()
        // Initialize ViewModel for each item
        data.forEachIndexed { position, _ ->
            initViewModel(position)
        }
    }

    private fun initViewModel(position: Int) {
        val itemId = getItemId(position)
        val viewType = getItemViewType(position)
        val viewModelClass = getViewModelClass(viewType)
        val viewModelTag = "${viewModelClass.simpleName}-$itemId"
        getViewModelProvider(position).get(viewModelTag, viewModelClass)
            .also {
                viewModels[itemId] = it
            }
    }

    private fun getViewModelProvider(position: Int) =
        ViewModelProvider(
            viewModelStoreOwner,
            getViewModelProviderFactory(getItemViewType(position), data[position])
        )

    abstract fun getViewModelClass(viewType: Int): Class<out ViewModel>

    abstract fun getViewModelProviderFactory(viewType: Int, item: T): ViewModelProvider.Factory

}
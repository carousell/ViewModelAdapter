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

    protected val data = mutableListOf<T>()

    override fun getItemCount() = data.size

    override fun getItemId(position: Int): Long = data[position].id

    override fun onBindViewHolder(holder: ViewModelHolder<ViewModel>, position: Int) {
        val viewType = getItemViewType(position)
        val viewModelClass = getViewModelClass(viewType)
        val viewModelTag = "${viewModelClass.simpleName}-${getItemId(position)}"
        val viewModel = getViewModelProvider(position).get(viewModelTag, viewModelClass)
        holder.bind(viewModel)
    }

    fun setData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private fun getViewModelProvider(position: Int) =
        ViewModelProvider(
            viewModelStoreOwner,
            getViewModelProviderFactory(getItemViewType(position), data[position])
        )

    abstract fun getViewModelClass(viewType: Int): Class<out ViewModel>

    abstract fun getViewModelProviderFactory(viewType: Int, item: T): ViewModelProvider.Factory

}
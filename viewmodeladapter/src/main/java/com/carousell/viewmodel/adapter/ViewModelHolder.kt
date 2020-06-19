package com.carousell.viewmodel.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Base ViewHolder for ViewModelAdapter, will bind or unbind with related ItemViewModel when screen scroll
 */
abstract class ViewModelHolder<T : ItemViewModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    /**
     * The ViewModel which ViewModelHolder bind to currently
     */
    var viewModel: T? = null

    /**
     * The entry point for ViewModelAdapter to bind ItemViewModel
     */
    @Suppress("UNCHECKED_CAST")
    fun bind(viewModel: ItemViewModel) {
        (viewModel as? T)?.let { bindInternal(it) }
    }

    /**
     * The implementation of bind(viewModel), will call unbind(), keep viewModel reference and onBind()
     */
    private fun bindInternal(viewModel: T) {
        unbind()
        this.viewModel = viewModel
        onBind(viewModel)
    }

    /**
     * Unbind event, will be called when new ViewModel is about to bind or the ViewHolder is been recycled.
     */
    fun unbind() {
        this.viewModel?.let {
            onUnbind(it)
        }
    }

    /**
     * Callback of bind event, with ViewModel its currently bind with
     */
    abstract fun onBind(viewModel: T)

    /**
     * Callback of unbind event, with ViewModel which will no-longer bind with
     */
    abstract fun onUnbind(viewModel: T)
}
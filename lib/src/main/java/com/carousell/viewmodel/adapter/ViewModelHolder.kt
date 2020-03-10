package com.carousell.viewmodel.adapter

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelHolder<T : ViewModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    var viewModel: T? = null

    @Suppress("UNCHECKED_CAST")
    fun bind(viewModel: ViewModel) {
        (viewModel as? T)?.let { bindInternal(it) }
    }

    private fun bindInternal(viewModel: T) {
        unbind()
        this.viewModel = viewModel
        onBind(viewModel)
    }

    fun unbind() {
        this.viewModel?.let {
            onUnbind(it)
        }
    }

    abstract fun onBind(viewModel: T)

    abstract fun onUnbind(viewModel: T)
}
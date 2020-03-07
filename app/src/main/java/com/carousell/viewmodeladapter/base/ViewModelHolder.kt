package com.carousell.viewmodeladapter.base

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelHolder<T : ViewModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var viewModel: T? = null

    fun bind(viewModel: T) {
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
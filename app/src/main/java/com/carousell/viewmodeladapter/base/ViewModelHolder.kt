package com.carousell.viewmodeladapter.base

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class ViewModelHolder<T : ViewModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var viewModel: T? = null

    fun bind(viewModel: T) {
        this.viewModel?.let {
            onUnbind(it)
        }
        this.viewModel = viewModel
        onBind(viewModel)
    }

    abstract fun onBind(viewModel: T)

    abstract fun onUnbind(viewModel: T)
}
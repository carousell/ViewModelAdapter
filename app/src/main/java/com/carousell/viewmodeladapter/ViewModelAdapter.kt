package com.carousell.viewmodeladapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.carousell.viewmodeladapter.base.ViewModelHolder

abstract class ViewModelAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<ViewModelHolder<ViewModel>>() {
    protected val data = mutableListOf<ViewModel>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewModelHolder<ViewModel>, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<ViewModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}
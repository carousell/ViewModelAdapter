package com.carousell.viewmodeladapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import com.carousell.viewmodel.adapter.ViewModelAdapter
import com.carousell.viewmodel.adapter.ViewModelHolder

class MainAdapter(
    private val lifecycleOwner: LifecycleOwner,
    viewModelStore: ViewModelStore
) : ViewModelAdapter<MyItem>(viewModelStore) {

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_EDIT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is MyItem.Text -> TYPE_TEXT
            is MyItem.Edit -> TYPE_EDIT
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewModelHolder<out ViewModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TEXT -> TextHolder(
                layoutInflater.inflate(R.layout.adapter_text, parent, false),
                lifecycleOwner
            )
            TYPE_EDIT -> EditHolder(
                layoutInflater.inflate(R.layout.adapter_edit, parent, false)
            )
            else -> throw RuntimeException("Not support")
        }
    }

    override fun getViewModelClass(viewType: Int): Class<out ViewModel> = when (viewType) {
        TYPE_TEXT -> TextViewModel::class.java
        TYPE_EDIT -> EditViewModel::class.java
        else -> throw IllegalArgumentException("View type $viewType is not implemented")
    }

    override fun getViewModelProviderFactory(viewType: Int, item: MyItem) = when (viewType) {
        TYPE_TEXT -> TextViewModel.Factory(item.key)
        TYPE_EDIT -> EditViewModel.Factory(item.key)
        else -> throw IllegalArgumentException("View type $viewType is not implemented")
    }
}
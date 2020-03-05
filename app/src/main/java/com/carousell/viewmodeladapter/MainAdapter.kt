package com.carousell.viewmodeladapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.carousell.viewmodeladapter.base.ViewModelHolder

class MainAdapter(lifecycleOwner: LifecycleOwner) : ViewModelAdapter(lifecycleOwner) {

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_EDIT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is TextViewModel -> TYPE_TEXT
            is EditViewModel -> TYPE_EDIT
            else -> throw RuntimeException("Not support")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewModelHolder<ViewModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TEXT -> TextHolder(
                layoutInflater.inflate(R.layout.adapter_text, parent, false),
                lifecycleOwner
            ) as ViewModelHolder<ViewModel>
            TYPE_EDIT -> EditHolder(
                layoutInflater.inflate(R.layout.adapter_edit, parent, false)
            ) as ViewModelHolder<ViewModel>
            else -> throw RuntimeException("Not support")
        }
    }
}
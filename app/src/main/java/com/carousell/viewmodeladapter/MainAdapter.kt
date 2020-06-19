package com.carousell.viewmodeladapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.carousell.viewmodel.adapter.ItemViewModel
import com.carousell.viewmodel.adapter.ViewModelAdapter
import com.carousell.viewmodel.adapter.ViewModelHolder
import com.carousell.viewmodeladapter.items.EditHolder
import com.carousell.viewmodeladapter.items.EditViewModel
import com.carousell.viewmodeladapter.items.TextHolder
import com.carousell.viewmodeladapter.items.TextViewModel

class MainAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ViewModelAdapter() {

    companion object {
        const val TYPE_TEXT = 1
        const val TYPE_EDIT = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TextViewModel -> TYPE_TEXT
            is EditViewModel -> TYPE_EDIT
            else -> throw RuntimeException("Not support")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewModelHolder<out ItemViewModel> {
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
}
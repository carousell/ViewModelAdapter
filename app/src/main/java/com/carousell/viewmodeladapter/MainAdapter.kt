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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        modelType: Class<out ItemViewModel>
    ): ViewModelHolder<out ItemViewModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (modelType) {
            EditViewModel::class.java -> EditHolder(
                layoutInflater.inflate(R.layout.adapter_edit, parent, false)
            )
            TextViewModel::class.java -> TextHolder(
                layoutInflater.inflate(R.layout.adapter_text, parent, false),
                lifecycleOwner
            )

            else -> throw RuntimeException("Not support")
        }
    }
}
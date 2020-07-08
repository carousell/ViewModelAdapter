package com.carousell.viewmodeladapter.items

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.carousell.viewmodel.adapter.ViewModelHolder
import kotlinx.android.synthetic.main.adapter_text.view.*

class TextHolder(itemView: View, private val lifecycleOwner: LifecycleOwner) :
    ViewModelHolder<TextViewModel>(itemView), Observer<String> {

    override fun onBind(viewModel: TextViewModel) {
        viewModel.liveData().observe(lifecycleOwner, this)
    }

    override fun onUnbind(viewModel: TextViewModel) {
        viewModel.liveData().removeObserver(this)
    }

    override fun onChanged(string: String?) {
        itemView.text.text = string
    }
}
package com.carousell.viewmodeladapter.items

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.carousell.viewmodel.adapter.ViewModelHolder
import kotlinx.android.synthetic.main.adapter_edit.view.*

class EditHolder(itemView: View) :
    ViewModelHolder<EditViewModel>(itemView), TextWatcher {

    override fun onBind(viewModel: EditViewModel) {
        itemView.edit.addTextChangedListener(this)
        itemView.edit.setText(viewModel.getValue())
    }

    override fun onUnbind(viewModel: EditViewModel) {
        itemView.edit.addTextChangedListener(null)
    }

    override fun afterTextChanged(p0: Editable?) {
        viewModel?.setValue(p0.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}